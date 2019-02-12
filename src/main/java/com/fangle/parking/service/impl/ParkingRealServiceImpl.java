package com.fangle.parking.service.impl;

import com.fangle.parking.mapper.ParkingRealMapper;
import com.fangle.parking.entity.ParkingReal;
import com.fangle.parking.mapper.ParkingRealMapper;
import com.fangle.parking.service.ParkingRealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingRealServiceImpl implements ParkingRealService {
    @Autowired
    private ParkingRealMapper parkingRealMapper;

    @Override
    public Integer insertParkingReal(ParkingReal parkingReal) {
        return parkingRealMapper.insertParkingReal(parkingReal);
    }

    @Override
    public List<ParkingReal> selectAllParkingReal() {
        return parkingRealMapper.selectAllParkingReal();
    }
}
