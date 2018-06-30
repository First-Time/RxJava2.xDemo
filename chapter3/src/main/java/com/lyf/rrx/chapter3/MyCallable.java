package com.lyf.rrx.chapter3;

import java.util.concurrent.Callable;

/**
 * Created by zinclee123 on 2018/6/14.
 */

public class MyCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println("模拟一些耗时的任务...");
        Thread.sleep(5000);
        return "OK";
    }
}
