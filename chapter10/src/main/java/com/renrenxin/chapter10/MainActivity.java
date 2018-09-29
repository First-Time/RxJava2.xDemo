package com.renrenxin.chapter10;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Scheduler;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observables.GroupedObservable;
import io.reactivex.parallel.ParallelFlowable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        并行
        //==========================P214(Java8新增了并行流来实现并行的效果，只需在集合上调用parallelStream()方法即可。)=================================
        /*List<Integer> result = new ArrayList<>();
        for (Integer i = 1; i <= 100; i++) {
            result.add(i);
        }

        result.parallelStream()
                .map(new java.util.function.Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) {
                        return integer.toString();
                    }
                }).forEach(new java.util.function.Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("s=" + s + ";Current Thread Name=" + Thread.currentThread().getName());
            }
        });*/

        //==========================P216(在RxJava中可以借助flatMap操作符来实现类似于Java8的并行执行效果。)=================================
        /*Observable.range(1, 100)
                .flatMap(integer -> Observable.just(integer)
                        .subscribeOn(Schedulers.computation())
                        .map(integer12 -> integer12.toString()))
                .subscribe(s -> System.out.println("flatMap: " + s));*/

        //==========================P217(我们还可以使用ExecutorService来创建一个Scheduler，对刚才的代码稍微做一些改动。)=================================
        /*int threadNum = Runtime.getRuntime().availableProcessors() + 1;
        ExecutorService executor = Executors.newFixedThreadPool(threadNum);
        final Scheduler scheduler = Schedulers.from(executor);
        Observable.range(1, 100)
                .flatMap(integer -> Observable.just(integer)
                        .subscribeOn(scheduler)
                        .map(integer1 -> integer1.toString()))
                .doFinally(() -> executor.shutdown())
                .subscribe(s -> System.out.println("executor: " + s));*/

        //==========================P219(通过Round-Robin算法实现并行。Round-Robin算法是最简单的一种负载均衡算法。它的原理是把来自用户的请求轮流分配给内部的服务器：从服务器1开始，知道服务器N，然后重新开始循环，也被称为哈希取模法，是非常常用的数据分片方法。Round-Robin算法的优点是简洁，它无须记录当前所有连接的状态，所以是一种无状态调度。)=================================
        /*final AtomicInteger batch = new AtomicInteger(0);

        Observable.range(1, 100)
                .groupBy(integer -> batch.getAndIncrement() % 5)
                .flatMap(new Function<GroupedObservable<Integer, Integer>, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(GroupedObservable<Integer, Integer> integerIntegerGroupedObservable) throws Exception {
                        return integerIntegerGroupedObservable.observeOn(Schedulers.io())
                                .map(integer -> integer.toString());
                    }
                })
                .subscribe(s -> System.out.println("executor: " + s));*/

        //==========================P220(我们也可以使用ExecrtorService创建Scheduler来替代Schedulers.io()。)=================================
        /*final AtomicInteger batch = new AtomicInteger(0);

        int threadNum = 5;
        ExecutorService executor = Executors.newFixedThreadPool(threadNum);
        Scheduler scheduler = Schedulers.from(executor);

        Observable.range(1, 100)
                .groupBy(integer -> batch.getAndIncrement() % threadNum)
                .flatMap((Function<GroupedObservable<Integer, Integer>, ObservableSource<?>>) integerIntegerGroupedObservable -> integerIntegerGroupedObservable
                        .observeOn(scheduler)
                        .map(integer -> integer.toString()))
                .subscribe(s -> System.out.println("executor: " + s));*/

        //==========================P222(ParalleFlowable：RxJava2.0.5版本新增了ParallelFlowable API，它允许并行地执行一些操作符，例如map、filter、concatMap、flatMap、collect、reduce等。)=================================
        /*ParallelFlowable parallelFlowable = Flowable.range(1, 100).parallel();

        parallelFlowable.runOn(Schedulers.io())
                .map(new Function<Integer, Object>() {
                    @Override
                    public Object apply(Integer integer) throws Exception {
                        return integer.toString();
                    }
                })
                .sequential()
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println(s);
                    }
                });*/
    }
}
