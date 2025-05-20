package com.lms.enums;

public enum Coupon {
    DIWALI(10),
    BLACK_FRIDAY(20),
    PONGAL(15);
    private int discount;
    Coupon(int discount) {
        this.discount=discount;
    }

    public int getDiscount() {
        return discount;
    }
}
