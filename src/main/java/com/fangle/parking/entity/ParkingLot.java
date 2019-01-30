package com.fangle.parking.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ParkingLot {
    String parkingLotId; //停车场ID',
    String parkingLotName; //停车场名称',
    Date effectTime; //停车后场有效时间',
    Date updateTime; //更新时间',
    Date createTime; //创建时间',
}
