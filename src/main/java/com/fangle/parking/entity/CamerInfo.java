package com.fangle.parking.entity;

import lombok.Data;

import java.util.Date;

@Data
public class CamerInfo {
    private String host;
    private Integer port;
    private String sn;
    private Date updateTime;
    private Date creatTime;
}
