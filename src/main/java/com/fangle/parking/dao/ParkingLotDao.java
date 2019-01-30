package com.fangle.parking.dao;

import com.fangle.parking.entity.ParkingLot;

import java.util.List;

public interface ParkingLotDao {
    List <ParkingLot> selectAllParkingLot();
}
