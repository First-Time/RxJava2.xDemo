package com.lyf.test.chapter5;

import java.util.List;

/**
 * Created by ${LYF} on 2018/6/30.
 */

public class User {
    public String userName;
    public List<Address> addresses;

    public static class Address {
        public String street;
        public String city;
    }
}
