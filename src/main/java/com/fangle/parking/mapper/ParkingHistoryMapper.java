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

    //分页查询历史记录
    List<ParkingHistory> queryParkingHistoryList(@Param("plateNumber") String plateNumber,
                                                 @Param("arriveTime") Date arriveTime,
                                                 @Param("leaveTime") Date leaveTime,
                                                 @Param("rowIndex") int rowIndex,
                                                 @Param("pageSize") int pageSize);

    //插入历史记录
    Integer insertParkingHistory(ParkingHistory parkingHistory);


}
