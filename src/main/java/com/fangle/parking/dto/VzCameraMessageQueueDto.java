package com.fangle.parking.dto;

import com.alibaba.fastjson.JSONObject;
import com.fangle.parking.socket.handle.VzSocketClientHandler;
import lombok.Data;

@Data
public class VzCameraMessageQueueDto {
    private VzSocketClientHandler handler;
    private JSONObject result;

    public VzCameraMessageQueueDto(VzSocketClientHandler handler, JSONObject result) {
        this.handler = handler;
        this.result = result;
    }
}
