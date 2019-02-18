package com.fangle.parking.enums;

public enum PayModeEnum {
    PAY_MONTHLY(0, "包月"),
    PAY_CASH(1, "现金"),
    PAY_NONE(2, "未支付"),
    PAY_LABLE(3, "标签"),
    PAY_CARD(4, "一卡通"),
    PAY_CHARGECENTER(5, "中央收费岗亭"),
    PAY_EWALLET(6, "电子钱包"),
    PAY_APP(7, "App在线支付"),
    PAY_WOACCOUNT(8, "微信公众号在线支付"),
    PAY_WECHAT(9, "微信扫码支付"),
    PAY_ALIPAY(10, "支付宝扫码支付"),
    PAY_UNICONSELF(11, "银联主动支付"),
    PAY_UNICONAUTO(12, "银联无感支付")
    ;

    private Integer payType;
    private String payTypeDescription;

    private PayModeEnum (Integer payType, String payTypeDescription){
        this.payType = payType;
        this.payTypeDescription = payTypeDescription;
    }

    public static PayModeEnum stateOf(int payType) {
        for (PayModeEnum state : values()) {
            if (state.getPayType() == payType) {
                return state;
            }
        }
        return null;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public String getPayTypeDescription() {
        return payTypeDescription;
    }

    public void setPayTypeDescription(String payTypeDescription) {
        this.payTypeDescription = payTypeDescription;
    }


}
