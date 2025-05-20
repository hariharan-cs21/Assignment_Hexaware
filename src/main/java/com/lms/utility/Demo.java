package com.lms.utility;

import com.lms.enums.City;

public class Demo {
    public static void main(String[] args) {
        System.out.println("Allowed Values");
        for(City c: City.values()){
            System.out.println(c);
        }
        String coupon="chennai";
        try{
            City myCoupon=City.valueOf(coupon.toUpperCase());
            System.out.println("Coupon Applied "+myCoupon);
        }
        catch (IllegalArgumentException e){
            System.out.println("invalid Coupon");
        }
        catch (NullPointerException e){
            System.out.println("Null not applicable");
        }
    }
}
