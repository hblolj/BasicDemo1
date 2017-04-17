package com.hblolj.rxjava2demo;

import com.google.gson.Gson;

/**
 * Created by hblolj on 2017/4/14.
 */

public class Person {

    private String name;
    private String job;
    private String age;
    private String sex;

    public Person(String name, String job, String age, String sex) {
        this.name = name;
        this.job = job;
        this.age = age;
        this.sex = sex;
    }

    public Person() {
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
