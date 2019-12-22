package com.fangle.parking.entity;

import lombok.Data;

import java.util.Date;

@Data
public class CamerInfo {
    private Integer areaId;    //相机所在区域
    private Integer cameraId;    //相机ID
    private String cameraHost;    //ip
    private Integer cameraPort;    //端口
    private String cameraName;    //相机名称（出入口名称）
    private Integer cameraType;    //0 入口 1 出口
    private Integer mainCameraNumber;    //主相机编号  0 表示为主相机
    private Date updateTime;
    private Date creatTime;
}
