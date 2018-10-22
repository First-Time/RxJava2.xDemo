package com.renrenxin.chapter16;

/**
 * Created by zinclee123 on 2018/10/22.
 */

public class User {

    private String name;

    public User() {
//        this("刘燕霏");
    }

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
