package com.fangle.parking.socket.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.io.*;

@Slf4j
public class VzDataUtils {
    public static final String get_rtsp_url = "{\"cmd\" :\"get_rtsp_uri\"}";
    public static final String getsnCmd = "{\"cmd\" :\"getsn\"}";
    public static final String triggerCmd = "{\"cmd\" :\"trigger\"}";
    public static final int DISABLE_PUSH = 0;
    public static final int ENABLE_PUSH = 1;
    public static final int JSON_FMT = 1;
    public static final int BIN_FMT = 0;
    public static final int DISABLE_IMAGE = 0;
    public static final int ENABLE_IMAGE = 1;
    public static final int BLOCK_TYPE_BIN_RESULT = 1;
    public static final int BLOCK_TYPE_IMAGE_DATA = 2;

    public static int ConvBytesToInt(byte[] buff, int offset) {
        //4bytes 转为int，要考虑机器的大小端问题
        int len, byteValue;
        len = 0;
        byteValue = (0x000000FF & ((int) buff[offset]));
        len += byteValue << 24;
        byteValue = (0x000000FF & ((int) buff[offset + 1]));
        len += byteValue << 16;
        byteValue = (0x000000FF & ((int) buff[offset + 2]));
        len += byteValue << 8;
        byteValue = (0x000000FF & ((int) buff[offset + 3]));
        len += byteValue;
        return len;
    }

    public static JSONObject OnRecv(byte[] data, int len) throws UnsupportedEncodingException {
        if (len > 20 * 1024) {
            //带图片数据
            return OnIVSResultRecv(data, len);
        } else {
            //普通的指令响应
            log.debug("普通的指令响应跳过");
            return null;
        }
    }

    private static JSONObject OnIVSResultRecv(byte[] data, int len) {
        //接收到识别结果的处理
        if (data[0] == 'I' && data[1] == 'R') {
            //二进制的结构体处理
            log.error("识别推送格式为BIN,不支持解码!");
            return null;
        } else {
            //json处理
            int pos = 0;
            while (true) {
                if (data[pos] == 0) {
                    break;
                }
                pos++;
            }

            //转换识别结果
            String ivs = null;
            try {
                ivs = new String(data, 0, pos, "GBK");
            } catch (UnsupportedEncodingException e) {
                log.error("识别推送格GBK编码失败:{}",e.getMessage());
                return null;
            }

            JSONObject plateResult = PlateResult(ivs);
            // 此处改为通过json来解析车牌识别结果,来获取车牌图片的大小
            int nImgSize = len - pos - 1;
            // 获取图片的大小
            if (SaveImage(data, pos + 1, nImgSize, "d:\\" + System.currentTimeMillis() / 1000 + "_" + plateResult.getString("license") + ".jpg") <= 0)
                log.error("识别图片保存失败:{}",plateResult.getString("license"));

            return plateResult;
        }
    }

    private static int SaveImage(byte[] buff, int pos, int len, String imgPath) {
        int ret = -1;
        try {
            DataOutputStream out = new DataOutputStream(new FileOutputStream(imgPath));
            out.write(buff, pos, len);
            out.close();
            ret = len;
        } catch (IOException io) {
            log.error("save image failed " + imgPath);
        }

        return ret;
    }

    private static JSONObject PlateResult(String jsonData) {
        try {
            JSONObject parser = new JSONObject();  //创建JSON解析器

            JSONObject jsonObject = (JSONObject) parser.parse(jsonData.toString());
            if (jsonObject == null || jsonObject.isEmpty())
                return null;

            // 解析PlateResult
            JSONObject jsonPlateResult = (JSONObject) jsonObject.get("PlateResult");
            if (jsonPlateResult == null || jsonPlateResult.isEmpty())
                return null;
            return jsonPlateResult;
        } catch (Exception e) {
            log.error("解析识别结果出错:{}", e.getMessage());
            return null;
        }
    }
}
