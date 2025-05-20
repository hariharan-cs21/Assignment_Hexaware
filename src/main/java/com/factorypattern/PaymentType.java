package com.factorypattern;

public enum PaymentType {
    UPI(1),NEFT(1),IMPS(1),RTGS(0);
    private int value;
    private PaymentType(int val){
        this.value=val;
    }
    public int getValue(){
        return value;
    }
}
