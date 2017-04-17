package com.hblolj.commonadapter.bean;

/**
 * Created by hblolj on 2017/3/3.
 */

public class Bean {

    private String title;
    private String content;
    private String time;
    private String phone;

    public Bean(String title, String content, String time, String phone) {
        this.title = title;
        this.content = content;
        this.time = time;
        this.phone = phone;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
