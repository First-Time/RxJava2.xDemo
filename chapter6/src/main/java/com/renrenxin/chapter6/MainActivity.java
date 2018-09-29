package com.renrenxin.chapter6;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.BiPredicate;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        RxJava的条件操作符
//        amb、ambWith给定多个Observable，只让第一个发射数据的Observable发射全部数据。
//        defaultIfEmpty发射来自原始Observable的数据，如果原始Observable没有发射数据，则发射一个默认数据。
//        skipUntil丢弃原始Observable发射的数据，直到第二个Observable发射了一个数据，然后发射原始Observable的剩余数据。
//        skipWhile丢弃原始Observable发射的数据，直到一个特定的条件为假，然后发射原始Observable的剩余数据。
//        takeUntil发射来自原始Observable的数据，直到第二个Observable发射了一个数据或一个通知。
//        takeWhile、takeWhileWithIndex发射原始Observable的数据，直到一个特定的条件为真，然后跳过剩余的数据。

//        RxJava的布尔操作符
//        all判断是否所有的数据项都满足某个条件。
//        contains判断Observable是否会发射一个指定的值。
//        exists、isEmpty判断Observable是否发射一个值。
//        sequenceEqual判断两个Observables发射的序列是否相等。
//
        //==========================P146(all判断是否所有的数据项都满足某个条件。)=================================
        /*Observable.just(1, 2, 3, 4, 5)
                .all(integer -> integer < 3)
                .subscribe(aBoolean -> System.out.println(aBoolean));*/

        //==========================P148(contains判断Observable是否会发射一个指定的值。)=================================
        /*Observable.just(2, 30, 22, 5, 60, 1)
                .contains(22)
                .subscribe(aBoolean -> System.out.println("contains(22):" + aBoolean));

        Observable.just(2, 30, 22, 5, 60, 1)
                .isEmpty()
                .subscribe(aBoolean -> System.out.println("isEmpty():" + aBoolean));*/

        //==========================P149(给定两个活多个Observable，它只发射首先发射数据或通知的那个Observable的所有数据。)=================================
        /*Observable.ambArray(
                Observable.just(1, 2, 3).delay(1, TimeUnit.SECONDS),
                Observable.just(4, 5, 6))*//**//*
                .subscribe(integer -> System.out.println("integer:" + integer));*/

        //==========================P151(defaultIfEmpty发射来自原始Observable的数据，如果原始Observable没有发射数据，则发射一个默认数据。)=================================
        /*Observable.empty()
                .defaultIfEmpty(8)
                .subscribe(o -> System.out.println("defaultIfEmpty():" + o));*/

        //==========================P152=================================
        /*Observable.empty()
                .switchIfEmpty(Observable.just(1, 2, 3))
                .subscribe(o -> System.out.println("defaultIfEmpty():" + o));*/

        //==========================P152(sequenceEqual判断两个Observables发射的序列是否相等。)=================================
        /*Observable.sequenceEqual(
                Observable.just(1, 2, 3, 4, 5),
                Observable.just(1, 2, 3, 4, 5, 6))
                .subscribe(aBoolean -> System.out.println("sequenceEqual:" + aBoolean));*/

        //==========================P154(sequenceEqual判断两个Observables发射的序列是否相等。)=================================
        /*Observable.sequenceEqual(
                Observable.just(1, 2, 3, 4, 5),
                Observable.just(1, 2, 3, 4, 5),
                ((integer, integer2) -> integer == integer2))
                .subscribe(aBoolean -> System.out.println("sequenceEqual:" + aBoolean));*/

        //==========================P155(skipUntil丢弃原始Observable发射的数据，直到第二个Observable发射了一个数据，然后发射原始Observable的剩余数据。)=================================
        /*Observable.intervalRange(1, 9, 0, 1, TimeUnit.MILLISECONDS)
                .skipUntil(Observable.timer(4, TimeUnit.MILLISECONDS))
                .subscribe(aLong -> System.out.println(String.valueOf(aLong) + "a"));*/

        //==========================P156(skipWhile丢弃原始Observable发射的数据，直到一个特定的条件为假，然后发射原始Observable的剩余数据。)=================================
        /*Observable.just(1, 2, 3, 4, 5)
                .skipWhile(integer -> integer <= 2)
                .subscribe(integer -> System.out.println(integer + "b"));*/

        //==========================P157(takeUntil发射来自原始Observable的数据，直到第二个Observable发射了一个数据或一个通知。)=================================
        /*Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .takeUntil(integer -> integer > 5)
                .subscribe(integer -> System.out.println(String.valueOf(integer) + "a"));*/

        //==========================P156(takeWhile、takeWhileWithIndex发射原始Observable的数据，直到一个特定的条件为假，然后跳过剩余的数据。)=================================
        /*Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .takeWhile(integer -> integer <= 5)
                .subscribe(integer -> System.out.println(String.valueOf(integer) + "b"),
                        throwable -> System.out.println(throwable.getMessage()),
                        () -> System.out.println("onComplete"));*/
    }
}
