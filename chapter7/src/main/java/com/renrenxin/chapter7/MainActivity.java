package com.renrenxin.chapter7;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.observables.ConnectableObservable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        RxJava合并操作符
//        startWith在数据序列的开头增加一项数据。
//        merge将多个Observable合并为一个。
//        mergeDelayError合并多个Observable，让没有错误的Observable都完成后再发射错误通知。
//        zip使用一个函数组合多个Observable发射的数据集合，然后再发射这个结果。
//        combineLatest当两个Observable中的任何一个发射了一个数据时，通过一个指定的函数组合每个Observable发射的最新数据（一共两个数据），然后发射这个函数的结果。
//        join、groupJoin无论何时，如果一个Observable发射了一个数据项，就需要在另一个Observable发射的数据项定义的时间窗口内，将两个Observable发射的数据合并发射。
//        switchOnNext将一个发射Observable的Observable转换成另一个Observable，后者发射这些Observable最近发射的数据。

//        RxJava连接操作符
//        ConnectableObservable.connect指示一个可连接的Observable开始发射数据。
//        Observable.publish将一个Observable转换为一个可连接的Observable。
//        Observable.replay确保所有的订阅者看到相同的数据序列，即使它们在Observable开始发射数据之后才订阅。
//        ConnectableObservable.refCount让一个可连接的Observable表现得像一个普通的Observable。

        //==========================P161(merge将多个Observable合并为一个。)=================================
        /*Observable<Integer> odds = Observable.just(1, 3, 5);
        Observable<Integer> evens = Observable.just(2, 4, 6);

//        odds.mergeWith(evens)
        Observable.merge(odds, evens)
                .subscribe(integer -> System.out.println("Next: " + integer),
                        throwable -> System.err.println("Error:" + throwable.getMessage()),
                        () -> System.out.println("onComplete."));*/

        //==========================P161(zip使用一个函数组合多个Observable发射的数据集合，然后再发射这个结果。)=================================
        /*Observable<Integer> odds = Observable.just(1, 3, 5, 7, 9);
        Observable<Integer> evens = Observable.just(2, 4, 6);

        Observable.zip(odds, evens, (integer, integer2) -> integer + integer2).subscribe(integer -> System.out.println("Next: " + integer),
                throwable -> System.err.println(throwable.getMessage()),
                () -> System.out.println("onComplete."));*/

        //==========================P167(combineLatest操作符的行为类似于zip，但是只有当原始的Observable中的每一个都发射了一条数据时zip才发射数据，而combineLatest则是当原始的Observable中任意一个发射了数据时就发射一条数据。当原始Observable的任何一个发射了一条数据时，combineLatest使用一个函数结合它们最近发射的数据，然后发射这个函数的返回值。)=================================
        /*Observable<Integer> odds = Observable.just(1, 3, 5);
        Observable<Integer> evens = Observable.just(2, 4, 6);

        Observable.combineLatest(odds, evens, (integer, integer2) -> integer + integer2).subscribe(integer -> System.out.println("Next: " + integer),
                throwable -> System.err.println(throwable.getMessage()),
                () -> System.out.println("onComplete."));*/

        //==========================P168(join操作符结合两个Observable发射的数据，基于时间窗口（针对每条数据特定的原则）选择待集合的数据项，将这些时间窗口实现为一些Observables，它们的生命周期从任何一条Observable发射的一条数据开始。当这个定义时间窗口的Observable发射了一条数据或者完成时，与这条数据关联的窗口也会关闭。只要这条数据的窗口是打开的，它就继续结合其他Observable发射的任何数据项。)=================================
        /*Observable<Integer> odds = Observable.just(1, 3, 5);
        Observable<Integer> evens = Observable.just(2, 4, 6);

        odds.join(evens, new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                return Observable.just(String.valueOf(integer)).delay(100, TimeUnit.MILLISECONDS);
            }
        }, new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                return Observable.just(String.valueOf(integer)).delay(200, TimeUnit.MILLISECONDS);
            }
        }, new BiFunction<Integer, Integer, String>() {
            @Override
            public String apply(Integer integer, Integer integer2) throws Exception {
                return integer + ":" + integer2;
            }
        }).subscribe(integer -> System.out.println("onNext: " + integer));*/

        /*Observable<Integer> odds = Observable.just(1, 3, 5).delay(200, TimeUnit.MILLISECONDS);
        Observable<Integer> evens = Observable.just(2, 4, 6);

        odds.join(evens, new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                return Observable.just(String.valueOf(integer)).delay(200, TimeUnit.MILLISECONDS);
            }
        }, new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                return Observable.just(String.valueOf(integer)).delay(200, TimeUnit.MILLISECONDS);
            }
        }, new BiFunction<Integer, Integer, String>() {
            @Override
            public String apply(Integer integer, Integer integer2) throws Exception {
                return integer + ":" + integer2;
            }
        }).subscribe(integer -> System.out.println("onNext: " + integer));*/

        /*Observable<Long> odds = Observable.interval(1, TimeUnit.SECONDS);
        Observable<Long> evens = Observable.interval(1, TimeUnit.SECONDS);

        odds.join(evens, new Function<Long, Observable<Long>>() {
            @Override
            public Observable<Long> apply(Long aLong) throws Exception {
                return Observable.timer(2, TimeUnit.SECONDS);
            }
        }, new Function<Long, Observable<Long>>() {
            @Override
            public Observable<Long> apply(Long aLong) throws Exception {
                return Observable.timer(1, TimeUnit.SECONDS);
            }
        }, new BiFunction<Long, Long, String>() {
            @Override
            public String apply(Long aLong, Long aLong2) throws Exception {
                return aLong + ":" + aLong2;
            }
        }).subscribe(integer -> System.out.println("onNext: " + integer));*/

        //==========================P172(如果想让一个Observable在发射数据之前先发射一个指定的数据序列，则可以使用startWith操作符。如果想在一个Observable发射数据的末尾追加一个数据序列，则可以使用concat操作符。)=================================
        /*Observable.just("Hello Java", "Hello Kotlin", "Hello Scala")
                .startWith("Hello Rx")
                .subscribe(s -> System.out.println(s));*/

        /*Observable.just("Hello Java", "Hello Kotlin", "Hello Scala")
                .startWithArray("Hello Groovy", "Hello Clojure")
                .subscribe(s -> System.out.println(s));*/

        /*Observable.just("Hello Java", "Hello Kotlin", "Hello Scala")
                .startWithArray("Hello Groovy", "Hello Clojure")
                .startWith("Hello Rx")
                .subscribe(s -> System.out.println(s));*/

        /*Observable.just("Hello Java", "Hello Kotlin", "Hello Scala")
                .startWithArray("Hello Groovy", "Hello Clojure")
                .startWith(Observable.just("Hello Rx"))
                .subscribe(s -> System.out.println(s));*/

        //==========================P175(Observable.publish将一个Observable转换为一个可连接的Observable。ConnectableObservable.connect指示一个可连接的Observable开始发射数据。)=================================
        /*SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Observable<Long> obs = Observable.interval(1, TimeUnit.SECONDS).take(6);
        ConnectableObservable<Long> connectableObservable = obs.publish();
        connectableObservable.subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Long aLong) {
                System.out.println("subscribe1:onNext:" + aLong + "->time:" + sdf.format(new Date()));
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("subscribe1:onError");
            }

            @Override
            public void onComplete() {
                System.out.println("subscribe1:onComplete");
            }
        });

        connectableObservable.delaySubscription(3, TimeUnit.SECONDS)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        System.out.println("subscribe2:onNext:" + aLong + "->time:" + sdf.format(new Date()));
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("subscribe2:onError");
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("subscribe2:onComplete");
                    }
                });
        connectableObservable.connect();*/

        //==========================P177(ConnectableObservable.refCount让一个可连接的Observable表现得像一个普通的Observable。)=================================
        /*SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Observable<Long> obs = Observable.interval(1, TimeUnit.SECONDS).take(6);
        ConnectableObservable<Long> connectableObservable = obs.publish();
        Observable obsRefCount = connectableObservable.refCount();
        obs.subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Long aLong) {
                System.out.println("subscribe1:onNext:" + aLong + "->time:" + sdf.format(new Date()));
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("subscribe1:onError");
            }

            @Override
            public void onComplete() {
                System.out.println("subscribe1:onComplete");
            }
        });

        obs.delaySubscription(3, TimeUnit.SECONDS)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        System.out.println("subscribe2:onNext:" + aLong + "->time:" + sdf.format(new Date()));
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("subscribe2:onError");
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("subscribe2:onComplete");
                    }
                });

        obsRefCount.subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Long aLong) {
                System.out.println("obsRefCount1:onNext:" + aLong + "->time:" + sdf.format(new Date()));
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("obsRefCount1:onError");
            }

            @Override
            public void onComplete() {
                System.out.println("obsRefCount1:onComplete");
            }
        });

        obsRefCount.delaySubscription(3, TimeUnit.SECONDS)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        System.out.println("obsRefCount2:onNext:" + aLong + "->time:" + sdf.format(new Date()));
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("obsRefCount2:onError");
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("obsRefCount2:onComplete");
                    }
                });*/

        //==========================P181(Observable.replay确保所有的订阅者看到相同的数据序列，即使它们在Observable开始发射数据之后才订阅。)=================================
        /*SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Observable<Long> obs = Observable.interval(1, TimeUnit.SECONDS).take(6);
        ConnectableObservable<Long> connectableObservable = obs.replay();
        connectableObservable.connect();
        connectableObservable.subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Long aLong) {
                System.out.println("subscribe1:onNext:" + aLong + "->time:" + sdf.format(new Date()));
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("subscribe1:onError");
            }

            @Override
            public void onComplete() {
                System.out.println("subscribe1:onComplete");
            }
        });

        connectableObservable.delaySubscription(3, TimeUnit.SECONDS)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        System.out.println("subscribe2:onNext:" + aLong + "->time:" + sdf.format(new Date()));
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("subscribe2:onError");
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("subscribe2:onComplete");
                    }
                });*/
    }
}
