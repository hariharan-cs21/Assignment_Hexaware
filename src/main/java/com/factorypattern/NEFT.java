package com.factorypattern;

public class NEFT implements Payment{


    @Override
    public double dailyrate() {
        return 10000;
    }
}
