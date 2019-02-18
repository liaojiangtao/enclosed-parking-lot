package com.fangle.parking.mapper;

/*
 * @Author Gentel
 * @Date 2019-02-13 23:15
 */

import com.fangle.parking.entity.ParkingHistory;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ParkingHistoryMapper {
    //插入历史记录
    Integer insertParkingHistory(ParkingHistory parkingHistory);
}
