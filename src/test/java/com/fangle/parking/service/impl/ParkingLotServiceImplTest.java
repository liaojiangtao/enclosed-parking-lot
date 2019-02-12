package com.fangle.parking.service.impl;

import com.fangle.parking.entity.ParkingLot;
import com.fangle.parking.service.ParkingLotService;
import com.fangle.parking.util.TimeUtil;
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

        Assert.assertEquals("100001", parkingLots.get(0).getParkingLotId());
    }

    @Test
    public void insertParkingLot() throws Exception {
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setParkingLotId("100002");
        parkingLot.setParkingLotName("惠恒大厦");
        parkingLot.setEffectTime(TimeUtil.StringToDate("2020-01-01 0:0:0"));

        Integer effect = parkingLotService.insertParkingLot(parkingLot);

        System.out.println(effect);
    }
}