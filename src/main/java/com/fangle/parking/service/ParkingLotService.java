package com.fangle.parking.service;

import com.fangle.parking.entity.ParkingLot;

import java.util.List;

public interface ParkingLotService {

    /** 查询所有停车场 */
    List<ParkingLot> selectAllParkingLot();

    /** 插入停车场信息 */
    Integer insertParkingLot(ParkingLot parkingLot);
}
