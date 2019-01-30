package com.fangle.parking.service.impl;

import com.fangle.parking.dao.ParkingLotDao;
import com.fangle.parking.entity.ParkingLot;
import com.fangle.parking.service.ParkingLotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ParkingLotServiceImpl implements ParkingLotService {
    @Autowired
    private ParkingLotDao parkingLotDao;

    @Override
    public List<ParkingLot> selectAllParkingLot() {
        return parkingLotDao.selectAllParkingLot();
    }
}
