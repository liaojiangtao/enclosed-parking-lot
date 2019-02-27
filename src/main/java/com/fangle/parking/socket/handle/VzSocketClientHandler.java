package com.fangle.parking.socket.handle;

import com.fangle.parking.socket.protocol.VZPackage;
import com.fangle.parking.socket.utils.CloseUtils;
import com.fangle.parking.socket.utils.VzDataUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Data
public class VzSocketClientHandler {
    private final String host;
    private final Integer port;
    private final Socket socket;
    private final ClientReadHandler readHandler;
    private final ClientWriteHandler writeHandler;
    private final ClientHandlerCallback clientHandlerCallback;
    private final ClientKeepAlive clientKeepAlive;
    private final String clientSn;

    public VzSocketClientHandler(String host, Integer port, String clientSn, ClientHandlerCallback clientHandlerCallback) throws IOException {
        this.host = host;
        this.port = port;
        this.socket = new Socket(host, port);
        this.readHandler = new ClientReadHandler(socket.getInputStream());
        this.writeHandler = new ClientWriteHandler(socket.getOutputStream());
        this.clientHandlerCallback = clientHandlerCallback;
        this.clientSn = clientSn;
        this.clientKeepAlive = new ClientKeepAlive();

        this.readHandler.start();        //启动接收
        this.clientKeepAlive.start();        //启动心跳

        /*配置相机触发车辆识别后推送格式*/
        writeHandler.send(VZPackage.ConfigFormat(1,1,1));
    }

    //退出
    public void exit() {
        readHandler.exit();
        writeHandler.exit();
        CloseUtils.close(socket);
    }

    public void send(byte[] msg) {
        writeHandler.send(msg);
    }

    private void exitBySelf() {
        exit();
        clientHandlerCallback.onSelfClosed(this);
    }

    class ClientKeepAlive extends Thread {
        private boolean done = false;

        @Override
        public void run() {
            super.run();
            do {
                try {
                    log.debug("{} keep alive:{}", host, System.currentTimeMillis()/1000);
                    byte[] buff = {'V', 'Z', 1, 0, 0, 0, 0, 0};
                    OutputStream out = socket.getOutputStream();
                    out.write(buff);
                    Thread.sleep(1000 * 5);
                } catch (Exception e) {
                    log.error("Error:" + e);
                }
            } while (!done);
        }
    }

    class ClientReadHandler extends Thread {
        private boolean done = false;
        private final InputStream inputStream;

        ClientReadHandler(InputStream inputStream) {
            this.inputStream = inputStream;
        }

        @Override
        public void run() {
            super.run();
            try {
                do {
                    InputStream inputStreamLen = socket.getInputStream();
                    Integer rcvLen = rcvMsgLen(inputStreamLen);
                    if (rcvLen > 0) {
                        // 读出数据段内容
                        byte[] rcvByte = new byte[rcvLen];
                        int totleRecvLen = 0;
                        int recvLen = 0;
                        InputStream inputStreamData = socket.getInputStream();
                        try {
                            while (totleRecvLen < rcvLen) {
                                recvLen = inputStreamData.read(rcvByte, totleRecvLen, rcvLen - totleRecvLen);
                                totleRecvLen += recvLen;
                            }
                        } catch (Exception e) {
                            continue;
                        }

                        // 通知到TCPServer
                        clientHandlerCallback.onNewMessageArrived(VzSocketClientHandler.this, rcvByte, rcvLen);
                    }
                } while (!done);
            } catch (Exception e) {
                if (!done) {
                    System.out.println("连接异常断开");
                    VzSocketClientHandler.this.exitBySelf();
                }
            } finally {
                // 连接关闭
                CloseUtils.close(inputStream);
            }
        }

        Integer rcvMsgLen(InputStream in) throws IOException {
            byte[] header = new byte[8];
            int recvLen = in.read(header, 0, 8);

            if (recvLen <= 0) {
                return -1;
            }

            if (header[0] != 'V' || header[1] != 'Z') {
                //格式不对
                return -1;
            }

            if (header[2] == 1) {
                //心跳包
                return 0;
            }

            return VzDataUtils.ConvBytesToInt(header, 4);
        }

        void exit() {
            done = true;
            CloseUtils.close(inputStream);
        }
    }


    class ClientWriteHandler {
        private boolean done = false;
        private final DataOutputStream dataOutputStream;
        private final ExecutorService executorService;   //创建一个线程池用于发送

        ClientWriteHandler(OutputStream outputStream) {
            this.dataOutputStream = new DataOutputStream(outputStream);
            this.executorService = Executors.newSingleThreadExecutor();
        }

        //退出
        void exit() {
            done = true;
            CloseUtils.close(dataOutputStream);
            executorService.shutdownNow();
        }

        //发送数据
        void send(byte[] msg) {
            if (done) {
                return;
            }
            executorService.execute(new WriteRunnable(msg));    //线程池发送数据
        }

        class WriteRunnable implements Runnable {
            private final byte[] msg;

            WriteRunnable(byte[] msg) {
                this.msg = msg;
            }

            @Override
            public void run() {
                if (ClientWriteHandler.this.done) {
                    return;
                }

                try {
                    ClientWriteHandler.this.dataOutputStream.write(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public interface ClientHandlerCallback {
        void onSelfClosed(VzSocketClientHandler handler);

        void onNewMessageArrived(VzSocketClientHandler handler, byte[] msg, Integer len);
    }
}
