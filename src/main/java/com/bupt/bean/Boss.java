package com.bupt.bean;

import org.springframework.beans.factory.annotation.Autowired;

public class Boss {

    private Car car;

//    public Boss() {
//    }

    public Boss(Car car) {
        this.car = car;
    }

    public Car getCar() {
        return car;
    }

//    @Autowired
    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Boss{" +
                "car=" + car +
                '}';
    }
}
