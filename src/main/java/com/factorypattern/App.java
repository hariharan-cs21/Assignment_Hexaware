package com.factorypattern;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Available Payment types");
        for(PaymentType type :PaymentType.values()) {
            System.out.println(type);
        }
        System.out.println("Enter your payment type");
        String payType=sc.next();
        try{
            PaymentType paymentType=PaymentType.valueOf(payType);
            if(paymentType.getValue()==0){
                System.out.println("In active Payment Gateway");
            }
            else{
                Payment payment=PaymentFactory.getInstance(paymentType);
                System.out.println("Dail limit is "+payment.dailyrate());
            }
        }
        catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
}
