package com.bupt.bean;

public class Phone {
    private String name;
    private String scen;
    private String app;

    public Phone() {
    }

    public Phone(String name, String scen, String app) {
        this.name = name;
        this.scen = scen;
        this.app = app;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScen() {
        return scen;
    }

    public void setScen(String scen) {
        this.scen = scen;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "name='" + name + '\'' +
                ", scen='" + scen + '\'' +
                ", app='" + app + '\'' +
                '}';
    }
}
