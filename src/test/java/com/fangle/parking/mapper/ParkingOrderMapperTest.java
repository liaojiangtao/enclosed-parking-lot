package com.fangle.parking.mapper;

import com.fangle.parking.entity.ParkingPayOrder;
import com.fangle.parking.enums.PayModeEnum;
import com.fangle.parking.mapper.ParkingPayOrderMapper;
import com.fangle.parking.util.TimeUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParkingOrderMapperTest {

    @Autowired
    private ParkingPayOrderMapper parkingPayOrderMapper;

    @Test
    public void insertParkingOrderTest() throws Exception {
        ParkingPayOrder parkingPayOrder = new ParkingPayOrder();
        parkingPayOrder.setParkingOrder("P201902151754010100001");
        parkingPayOrder.setPayOrder("O201902151754010100001");
        parkingPayOrder.setPayFee(1000);
        parkingPayOrder.setPayMode(PayModeEnum.PAY_CASH.getPayType());
        parkingPayOrder.setPayTime(TimeUtil.StringToDate("2019-02-15 18:00:00"));

        int effect = parkingPayOrderMapper.insertParkingPayOrder(parkingPayOrder);

        Assert.assertEquals(1, effect);
    }

    @Test
    public void queryParkingPayOrderTest() throws Exception {
        List<ParkingPayOrder> parkingPayOrders = parkingPayOrderMapper.queryParkingPayOrder("P201902151754010100001");
        Assert.assertEquals(1, parkingPayOrders.size());

    }

    @Test
    public void queryAllParkingPayOrder() throws Exception{
        List<ParkingPayOrder> parkingPayOrders = parkingPayOrderMapper.queryAllParkingPayOrder();
        Assert.assertEquals(1, parkingPayOrders.size());
    }
}
