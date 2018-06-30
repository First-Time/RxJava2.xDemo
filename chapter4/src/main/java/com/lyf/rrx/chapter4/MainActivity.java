package com.lyf.rrx.chapter4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.schedulers.TestScheduler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //==========================P82=================================
        /*Observable.create(new ObservableOnSubscribe<String>() {


            *//** Called for each Observer that subscribes.
             *
             * @param emitter the safe emitter instance, never null
             * @throws Exception on error*//*

            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("hello");
                emitter.onNext("world");
            }
        }).observeOn(Schedulers.newThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println(s);
                    }
                });*/

        //==========================P83=================================
        /*Observable.just("aaa", "bbb")
                .observeOn(Schedulers.newThread())
                .map(new Function<String, String>() {
                    @Override
                    public String apply(@NonNull String s) throws Exception {
                        return s.toUpperCase();
                    }
                })
                .subscribeOn(Schedulers.single())
                .observeOn(Schedulers.io())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println(s);
                    }
                });*/

        //==========================P97=================================
        /*Observable.create(new ObservableOnSubscribe<String>() {

            *//*
             * Called for each Observer that subscribes.
             *
             * @param emitter the safe emitter instance, never null
             * @throws Exception on error
             *//*
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("hello");
                emitter.onNext("world");
            }
        }).subscribeOn(Schedulers.newThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println(s);
                    }
                });*/

        //==========================P97=================================
        /*Observable.just("HELLO WORLD")
                .subscribeOn(Schedulers.single())
                .map(new Function<String, String>() {
                    @Override
                    public String apply(@NonNull String s) throws Exception {
                        s = s.toLowerCase();
                        L.i("map1", s);
                        return s;
                    }
                })
                .observeOn(Schedulers.io())
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        s = s + " tony.";
                        L.i("map2", s);
                        return s;
                    }
                })
                .subscribeOn(Schedulers.computation())
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        s = s + "it is a test.";
                        L.i("map3", s);
                        return s;
                    }
                })
                .observeOn(Schedulers.newThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        L.i("subscribe", s);
                        System.out.println(s);
                    }
                });*/

        //==========================P100=================================
        /*TestScheduler scheduler = new TestScheduler();
        scheduler.createWorker().schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("immediate");
            }
        });

        scheduler.createWorker().schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("20s");
            }
        }, 20, TimeUnit.SECONDS);

        scheduler.createWorker().schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("40s");
            }
        }, 40, TimeUnit.SECONDS);

        scheduler.advanceTimeTo(1, TimeUnit.MILLISECONDS);
        System.out.println("virtual time: " + scheduler.now(TimeUnit.MILLISECONDS));

        scheduler.advanceTimeTo(20, TimeUnit.SECONDS);
        System.out.println("virtual time: " + scheduler.now(TimeUnit.MILLISECONDS));

        scheduler.advanceTimeTo(40, TimeUnit.SECONDS);
        System.out.println("virtual time: " + scheduler.now(TimeUnit.MILLISECONDS));*/

        //==========================P101=================================
        /*TestScheduler scheduler = new TestScheduler();
        final AtomicLong atomicLong = new AtomicLong();
        Observable.timer(2, TimeUnit.SECONDS, scheduler).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                atomicLong.incrementAndGet();
            }
        });
        System.out.println("atomicLong's value=" + atomicLong.get() + ", virtual time:" + scheduler.now(TimeUnit.SECONDS));

        scheduler.advanceTimeBy(1, TimeUnit.SECONDS);

        System.out.println("atomicLong's value=" + atomicLong.get() + ", virtual time:" + scheduler.now(TimeUnit.SECONDS));

        scheduler.advanceTimeBy(1, TimeUnit.SECONDS);

        System.out.println("atomicLong's value=" + atomicLong.get() + ", virtual time:" + scheduler.now(TimeUnit.SECONDS));*/

        //==========================P102=================================
        /*TestScheduler scheduler = new TestScheduler();
        final AtomicLong atomicLong = new AtomicLong();
        Observable.timer(2, TimeUnit.SECONDS, scheduler).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                atomicLong.incrementAndGet();
            }
        });
        System.out.println("atomicLong's value=" + atomicLong.get() + ", virtual time:" + scheduler.now(TimeUnit.SECONDS));

        scheduler.advanceTimeBy(1, TimeUnit.SECONDS);

        System.out.println("atomicLong's value=" + atomicLong.get() + ", virtual time:" + scheduler.now(TimeUnit.SECONDS));

        scheduler.advanceTimeBy(-1, TimeUnit.SECONDS);

        System.out.println("atomicLong's value=" + atomicLong.get() + ", virtual time:" + scheduler.now(TimeUnit.SECONDS));

        scheduler.advanceTimeBy(2, TimeUnit.SECONDS);

        System.out.println("atomicLong's value=" + atomicLong.get() + ", virtual time:" + scheduler.now(TimeUnit.SECONDS));*/

        //==========================P103=================================
        TestScheduler scheduler = new TestScheduler();
        scheduler.createWorker().schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("immediate");
            }
        });

        scheduler.createWorker().schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("20s");
            }
        }, 20, TimeUnit.SECONDS);

//        scheduler.triggerActions();
        System.out.println("virtual time: " + scheduler.now(TimeUnit.SECONDS));

        scheduler.advanceTimeBy(20, TimeUnit.SECONDS);

        scheduler.triggerActions();
    }
}
