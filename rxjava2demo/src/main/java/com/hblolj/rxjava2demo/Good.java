package com.hblolj.rxjava2demo;

import com.google.gson.Gson;

/**
 * Created by hblolj on 2017/4/16.
 */

public class Good {

    private String goodName;
    private String price;

    public Good() {
    }

    public Good(String goodName, String price) {
        this.goodName = goodName;
        this.price = price;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
