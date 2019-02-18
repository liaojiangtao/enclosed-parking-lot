package com.fangle.parking.mapper;

import com.fangle.parking.entity.ParkingUnpayHistory;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ParkingUnpayHistoryMapper {
    //分页查询历史记录
    List<ParkingUnpayHistory> queryUnpayParkingHistoryList(@Param("plateNumber") String plateNumber,
                                                      @Param("arriveTime") Date arriveTime,
                                                      @Param("leaveTime") Date leaveTime,
                                                      @Param("rowIndex") int rowIndex,
                                                      @Param("pageSize") int pageSize);

    //插入历史记录
    Integer insertParkingUnpayHistory(ParkingUnpayHistory parkingUnpayHistory);
}
