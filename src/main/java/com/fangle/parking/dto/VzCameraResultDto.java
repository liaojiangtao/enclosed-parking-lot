package com.fangle.parking.dto;

import lombok.Data;

@Data
public class VzCameraResultDto {
    private String license;    // 车牌号码
    private Integer colorValue;    // 车牌颜色
    private Integer colorType;    // 车牌颜色序号，详见车牌颜色定义 LC_X
    private Integer type;    // 车牌类型，详见车牌类型定义 LT_X
    private Integer confidence;    // 车牌可信度
    private Integer direction;    // 运动方向，详见运动方向定义 DIRECTION_X
    private Integer carColor;    // 车的颜色，详见车辆颜色定义 LCOLOUR_X
    private Integer timeStamp;    // 识别时间点
    private Integer triggerType;    // 触发结果的类型,见 TH_TRIGGER_TYPE_BIT
}
