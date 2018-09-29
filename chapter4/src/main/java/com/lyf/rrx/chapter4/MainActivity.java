package com.lyf.rrx.chapter4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.safframework.log.L;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.schedulers.TestScheduler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //==========================P82=================================
        /*Observable.create((ObservableOnSubscribe<String>) emitter -> {
            emitter.onNext("hello");
            emitter.onNext("world");
        }).observeOn(Schedulers.newThread())
                .subscribe(s -> System.out.println(s));*/

        //==========================P83=================================
        /*Observable.just("aaa", "bbb")
                .observeOn(Schedulers.newThread())
                .map(s -> {
                    System.out.println(s + " " + Thread.currentThread().getName());
                    return s.toUpperCase();
                }).subscribeOn(Schedulers.trampoline())
                .observeOn(Schedulers.io())
                .subscribe(s -> System.out.println(s + " " + Thread.currentThread().getName()));*/

        //==========================P97=================================
       /* Observable.create((ObservableOnSubscribe<String>) emitter -> {
            emitter.onNext("hello");
            emitter.onNext("world");
        }).subscribeOn(Schedulers.newThread())
                .subscribe(s -> System.out.println(s));*/

        //==========================P97=================================
        /*Observable.just("HELLO WORLD")
                .subscribeOn(Schedulers.single())
                .map(s -> {
                    s = s.toLowerCase();
                    L.i("map1", s);
                    return s;
                })
                .observeOn(Schedulers.io())
                .map(s -> {
                    s = s + " tony.";
                    L.i("map2", s);
                    return s;
                })
                .subscribeOn(Schedulers.computation())
                .map(s -> {
                    s = s + "it is a test.";
                    L.i("map3", s);
                    return s;
                })
                .observeOn(Schedulers.newThread())
                .subscribe(s -> {
                    L.i("subscribe", s);
                    System.out.println(s);
                });*/

        //==========================P100=================================
        /*TestScheduler scheduler = new TestScheduler();
        scheduler.createWorker().schedule(() -> System.out.println("immediate"));

        scheduler.createWorker().schedule(() -> System.out.println("20s"), 20, TimeUnit.SECONDS);

        scheduler.createWorker().schedule(() -> System.out.println("40s"), 40, TimeUnit.SECONDS);

        scheduler.advanceTimeTo(1, TimeUnit.MILLISECONDS);
        System.out.println("virtual time: " + scheduler.now(TimeUnit.MILLISECONDS));

        scheduler.advanceTimeTo(20, TimeUnit.SECONDS);
        System.out.println("virtual time: " + scheduler.now(TimeUnit.MILLISECONDS));

        scheduler.advanceTimeTo(40, TimeUnit.SECONDS);
        System.out.println("virtual time: " + scheduler.now(TimeUnit.MILLISECONDS));*/

        //==========================P101=================================
        /*TestScheduler scheduler = new TestScheduler();
        final AtomicLong atomicLong = new AtomicLong();
        Observable.timer(2, TimeUnit.SECONDS, scheduler).subscribe(aLong -> atomicLong.incrementAndGet());
        System.out.println("atomicLong's value=" + atomicLong.get() + ", virtual time:" + scheduler.now(TimeUnit.SECONDS));

        scheduler.advanceTimeBy(1, TimeUnit.SECONDS);

        System.out.println("atomicLong's value=" + atomicLong.get() + ", virtual time:" + scheduler.now(TimeUnit.SECONDS));

        scheduler.advanceTimeBy(1, TimeUnit.SECONDS);

        System.out.println("atomicLong's value=" + atomicLong.get() + ", virtual time:" + scheduler.now(TimeUnit.SECONDS));*/

        //==========================P102=================================
        /*TestScheduler scheduler = new TestScheduler();
        final AtomicLong atomicLong = new AtomicLong();
        Observable.timer(2, TimeUnit.SECONDS, scheduler).subscribe(aLong -> atomicLong.incrementAndGet());
        System.out.println("atomicLong's value=" + atomicLong.get() + ", virtual time:" + scheduler.now(TimeUnit.SECONDS));

        scheduler.advanceTimeBy(1, TimeUnit.SECONDS);
        System.out.println("atomicLong's value=" + atomicLong.get() + ", virtual time:" + scheduler.now(TimeUnit.SECONDS));

        scheduler.advanceTimeBy(-1, TimeUnit.SECONDS);
        System.out.println("atomicLong's value=" + atomicLong.get() + ", virtual time:" + scheduler.now(TimeUnit.SECONDS));

        scheduler.advanceTimeBy(2, TimeUnit.SECONDS);
        System.out.println("atomicLong's value=" + atomicLong.get() + ", virtual time:" + scheduler.now(TimeUnit.SECONDS));*/

        //==========================P103=================================
        /*TestScheduler scheduler = new TestScheduler();
        scheduler.createWorker().schedule(() -> System.out.println("immediate"));

        scheduler.createWorker().schedule(() -> System.out.println("20s"), 20, TimeUnit.SECONDS);

        scheduler.triggerActions();
        System.out.println("virtual time: " + scheduler.now(TimeUnit.SECONDS));

        scheduler.advanceTimeBy(20, TimeUnit.SECONDS);

//        scheduler.triggerActions();*/
    }
}
