package com.fangle.parking.socket;

import com.alibaba.fastjson.JSONObject;
import com.fangle.parking.entity.CamerInfo;
import com.fangle.parking.socket.handle.VzSocketClientHandler;
import com.fangle.parking.socket.utils.VzDataUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class VzSocketClientApp implements VzSocketClientHandler.ClientHandlerCallback {
    private List<CamerInfo> camerInfoList = new ArrayList<>();
    private List<VzSocketClientHandler> clientHandlerList = new ArrayList<>();

    public void init() {
        CamerInfo camerInfo = new CamerInfo();
        camerInfo.setHost("192.168.6.241");
        camerInfo.setPort(8131);
        camerInfo.setSn("12434-12314-123123-123");
        camerInfoList.add(camerInfo);

        try {
            for (CamerInfo info : camerInfoList) {
                VzSocketClientHandler vzSocketClientHandler = new VzSocketClientHandler(info.getHost(), info.getPort(), info.getSn(), VzSocketClientApp.this);
                clientHandlerList.add(vzSocketClientHandler);
            }
        } catch (IOException e) {
            log.error("客户端连接异常：" + e.getMessage());
        }
    }

    @Override
    public void onSelfClosed(VzSocketClientHandler handler) {
        clientHandlerList.remove(handler);
    }

    @Override
    public void onNewMessageArrived(VzSocketClientHandler handler, byte[] msg, Integer len) {
        try {
            JSONObject result = VzDataUtils.OnRecv(msg, len);
            log.debug("jsonObjet:{}", result);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }

    }
}
