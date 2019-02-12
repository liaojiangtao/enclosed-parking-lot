package com.fangle.parking.mapper;

import com.fangle.parking.entity.ParkingLot;

import java.util.List;

public interface ParkingLotMapper {
    //查询所有停车场信息
    List <ParkingLot> selectAllParkingLot();

    //新建停车场
    Integer insertParkingLot(ParkingLot parkingLot);
}
