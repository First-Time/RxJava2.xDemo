package com.lyf.test.chapter5;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.observables.GroupedObservable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //==========================P82(map对Observable发射的每一项数据应用一个函数，执行变换操作。)=================================
        /*Observable.just("HELLO")
                .map(s -> s.toLowerCase())
                .map(s -> s + " world")
                .subscribe(s -> System.out.println(s));*/

        //==========================P110=================================
        /*User user = new User();
        user.userName = "tony";
        user.addresses = new ArrayList<>();
        User.Address address1 = new User.Address();
        address1.street = "ren ming road";
        address1.city = "Su zhou";
        user.addresses.add(address1);

        User.Address address2 = new User.Address();
        address2.street = "dong wu bei road";
        address2.city = "Su zhou";
        user.addresses.add(address2);

        Observable.just(user)
                .map(user1 -> user1.addresses)
        .subscribe(addresses -> {
            for (User.Address address : addresses) {
                System.out.println(address.street);
            }
        });*/

        //==========================P111(flatMap将一个发射数据的Observable变换为多个Observables，然后将它们发射的数据合并后放进一个单独的Observable。)=================================
        /*User user = new User();
        user.userName = "tony";
        user.addresses = new ArrayList<>();
        User.Address address1 = new User.Address();
        address1.street = "ren ming road";
        address1.city = "Su zhou";
        user.addresses.add(address1);

        User.Address address2 = new User.Address();
        address2.street = "dong wu bei road";
        address2.city = "Su zhou";
        user.addresses.add(address2);

        Observable.just(user)
                .flatMap(user1 -> Observable.fromIterable(user1.addresses))
                .subscribe(address -> System.out.println(address.street));*/

        //==========================P113(groupBy操作符将一个Observable拆分为一些Observables集合，它们中的每一项都发射原始Observable的一个子序列。)=================================
        /*Observable.range(1, 8)
                .groupBy(integer -> (integer % 2 == 0) ? "偶数组" : "奇数组")
                .subscribe(stringIntegerGroupedObservable -> {
//                    System.out.println("group name:" + stringIntegerGroupedObservable.getKey());
                    if (stringIntegerGroupedObservable.getKey().equalsIgnoreCase("奇数组")) {
                        stringIntegerGroupedObservable.subscribe(integer -> System.out.println(stringIntegerGroupedObservable.getKey() + "member: " + integer));
                    }
                });*/

        //==========================P115(buffer会定期收集Observable的数据并放进一个包裹，然后发射这些数据包裹，而不是每次发射一个值。)=================================
        /*Observable.range(1, 11)
                .buffer(5,1)
                .subscribe(integers -> System.out.println("onNext:" + integers),
                        throwable -> System.out.println(throwable.getMessage()),
                        () -> System.out.println("onComplete:"));*/

        //==========================P120(定期将来自原始Observable的数据分解为一个Observable窗口，发射这些窗口，而不是每次发射一项数据。)=================================
        /*Observable.range(1, 10)
                .window(2)
                .subscribe(new Consumer<Observable<Integer>>() {
                    @Override
                    public void accept(Observable<Integer> integerObservable) throws Exception {
                        System.out.println("onNext:");
                        integerObservable.subscribe(new Consumer<Integer>() {
                            @Override
                            public void accept(Integer integer) throws Exception {
                                System.out.println("accept:" + integer);
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                System.out.println(throwable.getMessage());
                            }
                        }, new Action() {
                            @Override
                            public void run() throws Exception {
                                System.out.println("onComplete:");
                            }
                        });
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println(throwable.getMessage());
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println("onComplete:");
                    }
                });*/

        //==========================P122(first只发射第一项(或者满足某个条件的第一项)数据。)=================================
        /*Observable.just(5,2,3)
                .first(1)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("Next: " + integer);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.err.println("Error:" + throwable.getMessage());
                    }
                });*/

        //==========================P123(first只发射第一项(或者满足某个条件的第一项)数据。)=================================
        /*Observable.<Integer>empty()
                .first(1)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("Next: " + integer);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.err.println("Error:" + throwable.getMessage());
                    }
                });*/

        //==========================P124(last只发射最后一项(或者满足某个条件的最后一项)数据。)=================================
        /*Observable.just(1,2,3)
                .last(5)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("Next: " + integer);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.err.println("Error:" + throwable.getMessage());
                    }
                });*/

        //==========================P125(take只发射前n项数据。)=================================
        /*Observable.just(1,2,3,4,5)
                .take(8)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("Next: " + integer);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.err.println("Error:" + throwable.getMessage());
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println("Sequence complete.");
                    }
                });*/

        //==========================P127(take只发射前n项数据。)=================================
        /*Observable.intervalRange(0, 10, 1, 1, TimeUnit.SECONDS)
                .take(5, TimeUnit.SECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        System.out.println("Next: " + aLong);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.err.println("Error:" + throwable.getMessage());
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println("Sequence complete.");
                    }
                });*/

        //==========================P128(takeLast发射Obserable发射的最后n项数据。)=================================
        /*Observable.just(1,2,3,4,5)
                .takeLast(3)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("Next: " + integer);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.err.println("Error:" + throwable.getMessage());
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println("Sequence complete.");
                    }
                });*/

        //==========================P130(takeLast发射Obserable发射的最后n项数据。)=================================
        /*Observable.intervalRange(0, 10, 1, 1, TimeUnit.SECONDS)
                .takeLast(3, TimeUnit.SECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        System.out.println("Next: " + aLong);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.err.println("Error:" + throwable.getMessage());
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println("Sequence complete.");
                    }
                });*/

        //==========================P131(Skip抑制Obserable发射的前n项数据。)=================================
        /*Observable.just(1,2,3,4,5)
                .skip(3)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("Next: " + integer);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.err.println("Error:" + throwable.getMessage());
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println("Sequence complete.");
                    }
                });*/

        //==========================P132(Skip抑制Obserable发射的前n项数据。)=================================
        /*Observable.intervalRange(0,10,1,1, TimeUnit.SECONDS)
                .skip(3, TimeUnit.SECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        System.out.println("Next: " + aLong);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.err.println("Error:" + throwable.getMessage());
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println("Sequence complete.");
                    }
                });*/

        //==========================P133(SkipLast抑制Obserable发射的后n项数据。)=================================
        /*Observable.just(1,2,3,4,5)
                .skipLast(3)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("Next: " + integer);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.err.println("Error:" + throwable.getMessage());
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println("Sequence complete.");
                    }
                });*/

        //==========================P134(SkipLast抑制Obserable发射的后n项数据。)=================================
        /*Observable.intervalRange(0,10,1,1, TimeUnit.SECONDS)
                .skip(3, TimeUnit.SECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        System.out.println("Next: " + aLong);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.err.println("Error:" + throwable.getMessage());
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println("Sequence complete.");
                    }
                });

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        //==========================P135(ElementAt只发射第n项数据。)=================================
        /*Observable.just(1, 2,3,4,5)
                .elementAt(2)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("Next: " + integer);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println("Error: " + throwable.getMessage());
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println("Sequence complete.");
                    }
                });*/

        //==========================P137(ElementAt只发射第n项数据(带默认值)。)=================================
        /*Observable.just(1, 2, 3, 4, 5)
                .elementAt(10, 0)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("Next: " + integer);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println("Error: " + throwable.getMessage());
                    }
                });*/

        //==========================P138(ignoreElements不发射任何数据，只发射Observable的终止通知。)=================================
        /*Observable.just(1, 2, 3, 4, 5)
                .ignoreElements()
                .subscribe(new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println("Sequence complete.");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println("Error: " + throwable.getMessage());
                    }
                });*/

        //==========================P139(distinct过滤掉重复的数据项，只允许还没有发射的数据项通过。)(distinct还能接受一个Function作为参数，这个函数根据原始Observable发射的数据项产生一个Key，比较这些Key而不是数据本身，来判定两个数据是否不同。)=================================
        /*Observable.just(1, 2, 1, 2, 3, 4, 5, 5, 6)
                .distinct()
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("Next: " + integer);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println("Error: " + throwable.getMessage());
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println("Sequence complete.");
                    }
                });*/

        //==========================P140(与distinct类似的是distinctUntilChanged操作符，该操作符与distinct的区别是，它只判定一个数据和它的直接前驱是否不同。)=================================
        /*Observable.just(1, 2, 1, 2, 3, 4, 5, 5, 6)
                .distinctUntilChanged()
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("Next: " + integer);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println("Error: " + throwable.getMessage());
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println("Sequence complete.");
                    }
                });*/

        //==========================P141(filter只发射通过谓词测试的数据项。)=================================
        /*Observable.just(2, 30, 2, 22, 5, 60, 1)
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return integer > 10;
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("Next: " + integer);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println("Error: " + throwable.getMessage());
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println("Sequence complete.");
                    }
                });*/

        //==========================P142(debounce仅在过了一段指定的时间还没发射数据时才发射一个数据。)=================================
        /*Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                if (emitter.isDisposed()) return;
                for (int i = 1; i <= 20; i++) {
                    emitter.onNext(i); //发射数据
                    Thread.sleep(i * 100);
                }
                *//*emitter.onNext(1); //发射数据
                Thread.sleep(300);
                emitter.onNext(2); //发射数据
                Thread.sleep(400);
                emitter.onNext(3); //发射数据
                Thread.sleep(501);
                emitter.onNext(4); //发射数据
                Thread.sleep(400);
                emitter.onNext(5); //发射数据
                Thread.sleep(600);
                emitter.onNext(6); //发射数据
                Thread.sleep(300);
                emitter.onNext(7); //发射数据
                Thread.sleep(300);
                emitter.onNext(8); //发射数据
                Thread.sleep(600);*//*
                emitter.onComplete();
            }
        }).debounce(500, TimeUnit.MILLISECONDS)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("Next: " + integer);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println("Error: " + throwable.getMessage());
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println("Sequence complete.");
                    }
                });*/
    }
}
