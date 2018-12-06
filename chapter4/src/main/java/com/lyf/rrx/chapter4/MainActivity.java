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

        /*
        * single：使用定长为1的线程池（newScheduledThreadPool(1)），重复利用这个线程。
        * newThread：每次都启用新线程，并在新线程中执行操作。
        * computation：使用的固定的线程池（Fixed Scheduler Pool），大小为cpu核数，适用于cpu密集型计算。
        * io：适合IO操作（读写文件、读写数据库、网络信息交互等）所使用的Scheduler。行为模式和newThread()差不多，区别在于io()的内部实现是一个无数量上限的线程池，可以重用空间的线程，因此大叔情况下，io()比newThread()更有效率。
        * trampoline：直接在当前线程运行，如果当前线程有其他任务正在执行，则会先暂停其他任务。
        * Schedulers.from：将java.util.concurrent.Executor转换成一个调度器实例，即可以自定义一个Executor来作为调度器。
        * */

        //==========================P82=================================
        /*Observable.create((ObservableOnSubscribe<String>) emitter -> {
            emitter.onNext("hello");
            emitter.onNext("world");
        }).observeOn(Schedulers.newThread())
                .subscribe(s -> System.out.println(s));*/

        //==========================P83=================================
        /*Observable.just("aaa", "bbb")
                .observeOn(Schedulers.newThread())
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        System.out.println(s + " " + Thread.currentThread().getName());
                        return s.toUpperCase();
                    }
                }).subscribeOn(Schedulers.trampoline())
                .observeOn(Schedulers.io())
                .subscribe(s -> System.out.println(s + " " + Thread.currentThread().getName()));*/

        //==========================P97=================================
        /*Observable.create((ObservableOnSubscribe<String>) emitter -> {
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

        //==========================P103（triggerActions不会修改时间，它执行计划中的但是未执行的任务，已经执行过的任务不会再启动。）=================================
        /*TestScheduler scheduler = new TestScheduler();
        scheduler.createWorker().schedule(() -> System.out.println("immediate"));

        scheduler.createWorker().schedule(() -> System.out.println("20s"), 20, TimeUnit.SECONDS);

        scheduler.triggerActions();
        System.out.println("virtual time: " + scheduler.now(TimeUnit.SECONDS));

        scheduler.advanceTimeBy(20, TimeUnit.SECONDS);*/

//        scheduler.triggerActions();
    }
}
