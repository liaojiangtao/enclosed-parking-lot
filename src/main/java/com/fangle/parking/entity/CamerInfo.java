package com.fangle.parking.entity;

import lombok.Data;

import java.util.Date;

@Data
public class CamerInfo {
    private String host;    //ip
    private Integer port;    //端口
    private String name;    //相机名称（出入口名称）
    private Integer type;    //0 入口 1 出口
    private Integer mainCameraNumber;    //主相机编号  0 表示为主相机
    private Date updateTime;
    private Date creatTime;
}
