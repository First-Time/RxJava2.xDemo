package com.renrenxin.chapter8;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        在RxJava中，会遇到被观察者发送消息太快以至于它的操作符或者订阅者不能及时处理相关的消息，这就是典型的背压(Back Pressure)场景。
//        MISSING:通过Create方法创建的Flowable没有指定背压策略，不会对通过OnNext发射的数据做缓存或丢弃处理，需要下游通过背压操作符(onBackpressureBuffer()/onBackpressureDrop()/onBackpressureLatest())指定背压策略。
//        ERROR:如果放入Flowable的异步缓存池中的数据超限了，则会抛出MissingBackpressureException异常。
//        BUFFER:Flowable的异步缓存池同Observable的一样，没有固定大小，可以无限制添加数据，不会抛出MissingBackpressureException异常，但会导致OOM(Out of Memory).
//        DROP:如果Flowable的异步缓存池满了，则会丢掉将要放入缓存池中的数据。
//        LATEST:如果缓存池满了，会丢掉将要放入缓存池中的数据。这一点与DROP策略一样，不同的是，不管缓存池的状态如何，LATEST策略会将最后一条数据强行放入缓存池中。

        //==========================P190(ERROR:Flowable的默认队列是128。)=================================
        /*Flowable.create((FlowableOnSubscribe<Integer>) emitter -> {
            for (int i = 0; i < 129; i++) {
                emitter.onNext(i);
            }
        }, BackpressureStrategy.ERROR)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(integer -> System.out.println(integer));*/

        //==========================P190(BUFFER:Flowable的异步缓存池同Observable的一样，没有固定大小，可以无限制添加数据，不会抛出MissingBackpressureException异常，但会导致OOM(Out of Memory)。)=================================
        /*Flowable.create((FlowableOnSubscribe<Integer>) emitter -> {
            for (int i = 0; ; i++) {
                emitter.onNext(i);
            }
        }, BackpressureStrategy.BUFFER)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(integer -> System.out.println(integer));*/

        //==========================P191(DROP:此策略表示，如果Flowable的异步缓存池满了，则会丢掉将要放入缓存池中的数据。)=================================
        /*Flowable.create((FlowableOnSubscribe<Integer>) emitter -> {
            for (int i = 0; i < 129; i++) {
                emitter.onNext(i);
            }
        }, BackpressureStrategy.DROP)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(integer -> System.out.println(integer));*/

        //==========================P192(LATEST:此策略表示，如果缓存池满了，会丢掉将要放入缓存池中的数据。这一点与DROP策略一样，不同的是，不管缓存池的状态如何，LATEST策略会将最后一条数据强行放入缓存池中。)=================================
        /*Flowable.create((FlowableOnSubscribe<Integer>) emitter -> {
            for (int i = 0; i < 1000; i++) {
                emitter.onNext(i);
            }
        }, BackpressureStrategy.LATEST)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(integer -> System.out.println(integer));*/
    }
}
