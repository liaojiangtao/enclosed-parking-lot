package com.fangle.parking.entity;

/*
 * @Author Gentel
 * @Date 2019-02-13 22:57
 */

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ParkingHistory {
    private String parkingOrder;    //停车订单
    private Integer shouldPay;    //应缴金额
    private Integer actualPay;    //实收金额
    private String plateNumber;    //车牌
    private Integer plateType;    //车牌类型
    private Integer plateColor;    //车牌颜色
    private Integer carColor;    //车辆颜色
    private Integer carType;    //车辆类型
    private Date arriveTime;    //入场时间
    private Integer entranceNum;    //停车场入口编号
    private String arrivePicUrl;    //入场图片url
    private Integer arrivePicUpStatus;    //入场图片上传状态
    private Integer arriveUpStatus;    //入场记录上传状态
    private Date leaveTime;    //出场时间
    private Integer exitNum;    //出口编号
    private String leavePicUrl;    //出场图片URL
    private Integer leavePicUpStatus;    //出场图片上传状态
    private Integer leaveUpStatus;    //出场记录上传状态
    private Integer enterType;    //入场类型 自动抬杆 手动抬杆
    private Integer leaveType;    //出场类型 自动抬杆 手动抬杆
    private Date createTime;    //创建时间
    private Date updateTime;    //更新时间
}
