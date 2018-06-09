package com.bupt.bean;


public class MathCalculator {

    @NeedPreLog
    public int div(int i, int j) {
//        System.out.println("this method has been invoked, res is " + i / j);
        return i / j;
    }

}
