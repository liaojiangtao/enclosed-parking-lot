package com.fangle.parking.entity;

import com.fangle.parking.enums.PayModeEnum;
import lombok.Data;

import java.util.Date;

@Data
public class ParkingPayOrder {
    private String parkingOrder;    //停车订单号
    private String payOrder;    //缴费订单号
    private Integer payMode;    //支付方式 PayModeEnum
    private Integer payFee;    //支付金额
    private Date payTime;    //支付时间
    private Date createTime;    //创建时间
}
