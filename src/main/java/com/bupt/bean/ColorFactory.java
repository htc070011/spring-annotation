package com.bupt.bean;

import org.springframework.beans.factory.FactoryBean;

public class ColorFactory implements FactoryBean<Color> {
    @Override
    public Color getObject() throws Exception {
        System.out.println("Color constructor.....");

        return new Color();
    }

    @Override
    public Class<?> getObjectType() {
        return Color.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
