package com.fangle.parking.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.fangle.parking.dto.VzCameraMessageQueueDto;
import com.fangle.parking.dto.VzCameraResultDto;
import com.fangle.parking.enums.VzCameraCmdEnum;
import com.fangle.parking.queue.CameraMessageQueue;
import com.fangle.parking.socket.protocol.VZPackage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Slf4j
public class VzCameraResultServiceImpl {
    private VzCameraResultServiceImpl vzCameraResultService = null;

    @PostConstruct
    public void init() {
        vzCameraResultService = new VzCameraResultServiceImpl();
        new Thread(new VzCameraResultServiceImpl.CameraMessageHandlerThread()).start();
    }

    class CameraMessageHandlerThread implements Runnable {

        boolean run = true;

        @Override
        public void run() {
            do {
                try {
                    if (!CameraMessageQueue.cameraMsg.isEmpty()) {
                        long timeStart = 0;
                        //读取一条数据出来处理
                        synchronized(CameraMessageQueue.cameraMsg) {
                            timeStart = System.currentTimeMillis();
                            VzCameraMessageQueueDto dto = CameraMessageQueue.cameraMsg.poll();
                            JSONObject msg = dto.getResult();
                            VzCameraResultDto vzCameraResult = new VzCameraResultDto();
                            vzCameraResult.setLicense(msg.getString("license"));    // 车牌号码
                            vzCameraResult.setColorValue(msg.getInteger("colorValue"));    // 车牌颜色
                            vzCameraResult.setColorType(msg.getInteger("colorType"));    // 车牌颜色序号，详见车牌颜色定义 LC_X
                            vzCameraResult.setType(msg.getInteger("type"));    // 车牌类型，详见车牌类型定义 LT_X
                            vzCameraResult.setConfidence(msg.getInteger("confidence"));    // 车牌可信度
                            vzCameraResult.setDirection(msg.getInteger("direction"));    // 运动方向，详见运动方向定义 DIRECTION_X
                            vzCameraResult.setCarColor(msg.getInteger("carColor"));    // 车的颜色，详见车辆颜色定义 LCOLOUR_X
                            vzCameraResult.setTimeStamp(msg.getJSONObject("timeStamp").getJSONObject("Timeval").getInteger("sec"));    // 识别时间点
                            vzCameraResult.setTriggerType(msg.getInteger("triggerType"));    // 触发结果的类型,见 TH_TRIGGER_TYPE_BIT
                           //VzCameraResultDto(license=粤A12345, colorValue=0, colorType=1, type=1, confidence=98, direction=4, carColor=0, timeStamp=1551693445, triggerType=8)
                            //如果为入口先抬闸后处理数据
                            dto.getHandler().send(VZPackage.GateControl(VzCameraCmdEnum.GATE_OPEN.getType()));
                            log.debug("处理相机触发时间时间{}毫秒", System.currentTimeMillis() - timeStart);
                        }
                    }
                    Thread.sleep(100);
                } catch (Exception e) {
                    log.error("处理相机消息出错:" + e);
                }
            } while (run);
        }
    }
}
