package com.fangle.parking.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class ParkingOrderUtil {
    private static final Random r = new Random();
    private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

    public static String GenerateParkingOrder(Integer gateId) {
        String result;
        int rannum = r.nextInt(89999) + 10000;
        if (gateId > 99)
            result = "P" + sDateFormat.format(new Date()) + "00" + rannum;
        else if (gateId > 9 ){
            result = "P" + sDateFormat.format(new Date()) + gateId + rannum;
        }else{
            result = "P" + sDateFormat.format(new Date()) + "0" + gateId + rannum;
        }

        return result;
    }
}
