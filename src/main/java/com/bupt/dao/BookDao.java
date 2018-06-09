package com.bupt.dao;

import org.springframework.stereotype.Repository;

@Repository
public class BookDao {
    int label;

    public BookDao() {
        this.label = 0;
    }

    public BookDao(int label) {
        this.label = label;
    }

    public int getLabel() {
        return label;
    }

    public void setLabel(int label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "BookDao{" +
                "label=" + label +
                '}';
    }
}
