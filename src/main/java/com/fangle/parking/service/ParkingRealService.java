package com.fangle.parking.service;

import com.fangle.parking.entity.ParkingReal;

import java.util.List;

public interface ParkingRealService {

    //插入实时停车记录
    Integer insertParkingReal(ParkingReal parkingReal);

    // 查询所有在场车
    List<ParkingReal> selectAllParkingReal();
}
