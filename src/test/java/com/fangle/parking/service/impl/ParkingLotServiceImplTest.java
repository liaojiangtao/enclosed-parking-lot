package com.fangle.parking.service.impl;

import com.fangle.parking.entity.ParkingLot;
import com.fangle.parking.service.ParkingLotService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.Max;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParkingLotServiceImplTest {
    @Autowired
    ParkingLotService parkingLotService;

    @Test
    public void selectAllParkingLot() {
        List<ParkingLot> parkingLots = parkingLotService.selectAllParkingLot();

        Assert.assertEquals("100000", parkingLots.get(0).getParkingLotId());
    }
}