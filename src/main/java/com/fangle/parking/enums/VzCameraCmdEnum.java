package com.fangle.parking.enums;

public enum VzCameraCmdEnum {
    GATE_OPEN(0, "{\"cmd\":\"ioctl\",\"io\" :0,\"value\":2,\"delay\":500}"),
    GATE_CLOSE(1, "{\"cmd\":\"ioctl\",\"io\" :0,\"value\":1,\"delay\":500}")
    ;

    private Integer type;
    private String cmd;

    private VzCameraCmdEnum (Integer type, String Cmd){
        this.type = type;
        this.cmd = Cmd;
    }

    public static VzCameraCmdEnum stateOf(int payType) {
        for (VzCameraCmdEnum state : values()) {
            if (state.getType() == payType) {
                return state;
            }
        }
        return null;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        cmd = cmd;
    }
}
