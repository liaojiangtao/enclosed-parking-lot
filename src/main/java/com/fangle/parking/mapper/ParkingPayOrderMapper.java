package com.fangle.parking.mapper;

import com.fangle.parking.entity.ParkingPayOrder;

import java.util.List;

public interface ParkingPayOrderMapper {

    //插入支付订单
    Integer insertParkingPayOrder(ParkingPayOrder parkingPayOrder);

    //查询支付订单
    List<ParkingPayOrder> queryParkingPayOrder(String parkingOrder);

    //查询所有支付订单
    List<ParkingPayOrder> queryAllParkingPayOrder();
}
