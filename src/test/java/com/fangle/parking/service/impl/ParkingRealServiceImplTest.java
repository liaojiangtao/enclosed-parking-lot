package com.fangle.parking.service.impl;

import com.fangle.parking.entity.ParkingReal;
import com.fangle.parking.service.ParkingRealService;
import com.fangle.parking.util.TimeUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/*
 * @Author Gentel
 * @Date 2019-02-13 22:41
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParkingRealServiceImplTest {

    @Autowired
    private ParkingRealService parkingRealService;

    @Test
    public void insertParkingReal() throws Exception {
        ParkingReal parkingReal = new ParkingReal();

        parkingReal.setParkingOrder("P201902131045010100001");
        parkingReal.setPlateNumber("粤B11111");
        parkingReal.setPlateColor(0);
        parkingReal.setCarColor(0);
        parkingReal.setCarType(0);
        parkingReal.setArriveTime(TimeUtil.StringToDate("2019-02-13 10:45:01"));
        parkingReal.setEntranceNum(0);
        parkingReal.setArrivePicUrl("");
        parkingReal.setArrivePicUpStatus(0);
        parkingReal.setArriveUpStatus(0);
        parkingReal.setEnterType(0);

        int effect = parkingRealService.insertParkingReal(parkingReal);
        Assert.assertEquals(1, effect);
    }

    @Test
    public void selectAllParkingReal() {
        List<ParkingReal> parkingReals = parkingRealService.selectAllParkingReal();
        Assert.assertEquals(1, parkingReals.size());
    }
}