package com.fangle.parking.socket;

import com.alibaba.fastjson.JSONObject;
import com.fangle.parking.dto.VzCameraMessageQueueDto;
import com.fangle.parking.entity.CamerInfo;
import com.fangle.parking.queue.CameraMessageQueue;
import com.fangle.parking.socket.handle.VzSocketClientHandler;
import com.fangle.parking.socket.utils.VzDataUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class VzSocketClientApp implements VzSocketClientHandler.ClientHandlerCallback {
    private VzSocketClientApp vzSocketClientApp = null;
    private List<CamerInfo> camerInfoList = new ArrayList<>();
    private List<VzSocketClientHandler> clientHandlerList = new ArrayList<>();

    @PostConstruct
    public void init() {
        vzSocketClientApp = new VzSocketClientApp();
        CamerInfo camerInfo = new CamerInfo();
        camerInfo.setHost("192.168.6.241");
        camerInfo.setPort(8131);
        camerInfo.setName("出口");
        camerInfoList.add(camerInfo);

        try {
            for (CamerInfo info : camerInfoList) {
                VzSocketClientHandler vzSocketClientHandler = new VzSocketClientHandler(info.getHost(), info.getPort(), info.getName(), VzSocketClientApp.this);
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
            if (null != result) {
                CameraMessageQueue.cameraMsg.add(new VzCameraMessageQueueDto(handler, result));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }

    }
}
