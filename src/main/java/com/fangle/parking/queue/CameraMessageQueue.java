package com.fangle.parking.queue;

import com.fangle.parking.dto.VzCameraMessageQueueDto;

import java.util.ArrayDeque;
import java.util.Queue;

public class CameraMessageQueue {

//    {
//        "timeUsed": 0,
//            "confidence": 98,
//            "bright": 0,
//            "carBright": 0,
//            "type": 1,
//            "colorType": 1,
//            "timeStamp": {
//        "Timeval": {
//            "sec": 1551686868,
//                    "usec": 298455
//        }
//    },
//        "license": "粤A12345",
//            "carColor": 0,
//            "colorValue": 0,
//            "location": {
//        "RECT": {
//            "top": 303,
//                    "left": 391,
//                    "bottom": 329,
//                    "right": 480
//        }
//    },
//        "triggerType": 8,
//            "direction": 4
//    }

    public static Queue<VzCameraMessageQueueDto> cameraMsg = new ArrayDeque<VzCameraMessageQueueDto>();
}
