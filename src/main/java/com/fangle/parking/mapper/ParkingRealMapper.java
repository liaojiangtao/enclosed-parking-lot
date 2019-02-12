package com.fangle.parking.mapper;

import com.fangle.parking.entity.ParkingReal;

import java.util.List;

public interface ParkingRealMapper {

    // 插入实时入场数据
    Integer insertParkingReal(ParkingReal parkingReal);

    // 查询所有在场车记录
    List<ParkingReal> selectAllParkingReal();
}
