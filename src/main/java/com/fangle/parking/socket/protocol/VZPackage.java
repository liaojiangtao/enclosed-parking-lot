package com.fangle.parking.socket.protocol;

public class VZPackage {
    public static byte[] ConfigFormat(Integer enable, Integer fmt, Integer image) {
        String cmd;
        cmd = String.format("{" +
                        "\"cmd\" : \"ivsresult\"," +
                        "\"enable\" : %s," +
                        "\"format\" : \"%s\"," +
                        "\"image\" : %s" +
                        "}",
                enable != 0 ? "true" : "false",
                fmt != 0 ? "json" : "bin",
                image != 0 ? "true" : "false");

        int len =  cmd.getBytes().length;
        byte[] header = {'V','Z',0,0,0,0,0,0};
        header[4] += (byte) ((len >>24) & 0xFF);
        header[5] += (byte) ((len >>16) & 0xFF);
        header[6] += (byte) ((len >>8) & 0xFF);
        header[7] += (byte) (len & 0xFF);

        byte[] cmdByte = new byte[len + 8];

        System.arraycopy(header, 0, cmdByte, 0, 8);
        System.arraycopy(cmd.getBytes(), 0, cmdByte, 8, cmd.getBytes().length);
        return cmdByte;
    }
}
