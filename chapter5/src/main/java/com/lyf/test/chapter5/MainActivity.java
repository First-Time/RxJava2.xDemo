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

        //==========================P109(map对Observable发射的每一项数据应用一个函数，执行变换操作。)=================================
        /*Observable.just("HELLO")
                .map(s -> s.toLowerCase())
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        return s + " world";
                    }
                })
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
                .flatMap(new Function<User, ObservableSource<User.Address>>() {
                    @Override
                    public ObservableSource<User.Address> apply(User user) throws Exception {
                        return Observable.fromIterable(user.addresses);
                    }
                })
                .subscribe(address -> System.out.println(address.street));*/

        //==========================P113(groupBy操作符将一个Observable拆分为一些Observables集合，它们中的每一项都发射原始Observable的一个子序列。)=================================
//        哪个数据项由哪一个Observable发射是由一个函数判定的，这个函数给每一项指定一个Key，Key相同的数据会被同一个Observable发射。
//        最终返回的是Observable的一个特殊子类GroupedObservable。它是一个抽象类，getKey()方法是GroupedObservable的方法，这个Key用于将数据分组到指定的Observable。
        /*Observable.range(1, 8)
                .groupBy(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) throws Exception {
                        return (integer % 2 == 0) ? "偶数组" : "奇数组";
                    }
                }).subscribe(new Consumer<GroupedObservable<String, Integer>>() {
            @Override
            public void accept(GroupedObservable<String, Integer> stringIntegerGroupedObservable) throws Exception {
                System.out.println("groupName:" + stringIntegerGroupedObservable.getKey());
            }
        });*/

//        对上述代码进行一些修改，对GroupedObservable使用getKey()方法，从而能够选出奇数组的GroupedObservable，最后打印出该GroupedObservable下的全部成员。
        /*Observable.range(1, 8)
                .groupBy(integer -> (integer % 2 == 0) ? "偶数组" : "奇数组")
                .subscribe(stringIntegerGroupedObservable -> {
                    if (stringIntegerGroupedObservable.getKey().equalsIgnoreCase("奇数组")) {
                        stringIntegerGroupedObservable.subscribe(integer -> System.out.println(stringIntegerGroupedObservable.getKey() + "member: " + integer));
                    }
                });*/

        //==========================P115(buffer会定期收集Observable的数据并放进一个包裹，然后发射这些数据包裹，而不是每次发射一个值。)=================================
//        buffer操作符将一个Observable变换为另一个，原来的Observable正常发射数据，由变换产生的Observable发射这些数据的缓存数据。
//        查看buffer操作符的源码，可以看到使用buffer操作符之后转换成Observable<List<T>>。
        /*Observable.range(1, 10)
                .buffer(2)
                .subscribe(integers -> System.out.println("onNext:" + integers),
                        throwable -> System.out.println(throwable.getMessage()),
                        () -> System.out.println("onComplete:"));*/

//        对上述代码做一些改动，将发射的数据变成11。
        /*Observable.range(1, 11)
                .buffer(2)
                .subscribe(integers -> System.out.println("onNext:" + integers),
                        throwable -> System.out.println(throwable.getMessage()),
                        () -> System.out.println("onComplete:"));*/

//        对上述代码再做一些改动，缓存5个数字。
        /*Observable.range(1, 11)
                .buffer(5)
                .subscribe(integers -> System.out.println("onNext:" + integers),
                        throwable -> System.out.println(throwable.getMessage()),
                        () -> System.out.println("onComplete:"));*/

//        在RxJava中有许多buffer的重载方法，例如比较常用的buffer(count, skip)。
//        buffer(count, skip)从原始Observable的第一项数据开始创建新的缓存，此后每当收到skip项数据，就用count想数据填充缓存：开头的一项和后续的count - 1 项。
//        它以列表(List)的形式发射缓存，这些缓存可能有重叠部分（比如skip < count时），也可能会有间隙（比如skip > count时)，取决于count和skip的值。
        /*Observable.range(1, 11)
                .buffer(5, 1)
                .subscribe(integers -> System.out.println("onNext:" + integers),
                        throwable -> System.out.println(throwable.getMessage()),
                        () -> System.out.println("onComplete:"));*/

//        如果原来的Observable发射了一个onError通知，那么buffer会立即传递这个通知，而不是首先发射缓存的数据，即使在这之前缓存中包含了原始Observable发射的数据。

        //==========================P120(window：定期将来自原始Observable的数据分解为一个Observable窗口，发射这些窗口，而不是每次发射一项数据。)=================================
//        window发射的不是原始Observable的数据包，而是Observable，这些Observable中的每一个都发射原始Observable数据的一个子集，最后发射一个onComplete通知。
        /*Observable.range(1, 10)
                .window(2)
                .subscribe(integerObservable -> {
                            System.out.println("onNext:");
                            integerObservable.subscribe(integer -> System.out.println("accept:" + integer),
                                    throwable -> System.out.println(throwable.getMessage()),
                                    () -> System.out.println("onComplete:"));
                        },
                        throwable -> System.out.println(throwable.getMessage()),
                        () -> System.out.println("onComplete:"));*/

        //==========================P122(first只发射第一项(或者满足某个条件的第一项)数据。)=================================
//        使用first()会返回Single类型。
        /*Observable.just(5, 2, 3)
                .first(1)
                .subscribe(integer -> System.out.println("Next: " + integer),
                        throwable -> System.err.println("Error:" + throwable.getMessage()));*/

//        如果Observable不发射任何数据，那么first操作符的默认值就起了作用。
        /*Observable.<Integer>empty()
                .firstOrError()
//                .first(1)
                .subscribe(integer -> System.out.println("Next: " + integer),
                        throwable -> System.err.println("Error:" + throwable.getMessage()));*/

//        在RxJava2.x中，还有firstElement操作符表示只取第一个数据，没有默认值。
//        firstOrError操作符表示要么能取到第一个数据，要么执行onError方法，它们分别返回Maybe类型和Single类型。

        //==========================P124(last只发射最后一项(或者满足某个条件的最后一项)数据。)=================================
        /*Observable.just(1, 2, 3)
                .last(5)
                .subscribe(integer -> System.out.println("Next: " + integer),
                        throwable -> System.err.println("Error:" + throwable.getMessage()));*/

//        在RxJava2.x中，有lastElement操作符和lastError操作符。

        //==========================P125(take只发射前面的n项数据。)=================================
        /*Observable.just(1, 2, 3, 4, 5)
                .take(3)
                .subscribe(integer -> System.out.println("Next: " + integer),
                        throwable -> System.err.println("Error:" + throwable.getMessage()),
                        () -> System.out.println("Sequence complete."));*/

//        如果对一个Observable使用take(n)操作符，而那个Observable发射的数据少于n项，那么take操作符生成的Observable就不会抛出异常或者发射onError通知，而是仍然会发射那么数据。

        /*Observable.just(1, 2, 3, 4, 5)
                .take(8)
                .subscribe(integer -> System.out.println("Next: " + integer),
                        throwable -> System.err.println("Error:" + throwable.getMessage()),
                        () -> System.out.println("Sequence complete."));*/

//        take有一个重载方法能够接受一个时长而不是数量参数，它只发射Observable开始的那段时间发射的数据，时长和时间单位通过参数指定。
//        takeLast的这个重载方法默认在computation调度器上执行，也可以使用参数来指定其他的调度器。
        /*Observable.intervalRange(0, 10, 1, 1, TimeUnit.SECONDS)
                .take(5, TimeUnit.SECONDS)
                .subscribe(aLong -> System.out.println("Next: " + aLong),
                        throwable -> System.err.println("Error:" + throwable.getMessage()),
                        () -> System.out.println("Sequence complete."));*/

        //==========================P128(takeLast发射Observable发射的最后n项数据。)=================================
//        使用takeLast操作符修改原始Observable，我们可以只发射Observable发射的最后n项数据，忽略前面的数据。
        /*Observable.just(1, 2, 3, 4, 5)
                .takeLast(3)
                .subscribe(integer -> System.out.println("Next: " + integer),
                        throwable -> System.err.println("Error:" + throwable.getMessage()),
                        () -> System.out.println("Sequence complete."));*/

//        同样，如果对一个Observable使用takeLast(n)操作符，而那个Observable发射的数据少于n项，那么takeLast操作符生成的Observable不会抛出异常或者发射onError通知，而是仍然发射那些数据。
        /*Observable.just(1, 2, 3, 4, 5)
                .takeLast(6)
                .subscribe(integer -> System.out.println("Next: " + integer),
                        throwable -> System.err.println("Error:" + throwable.getMessage()),
                        () -> System.out.println("Sequence complete."));*/

//        takeLast也有一个重载方法能够接受一个时长而不是数量参数，它会发射在原始Observable生命周期内最后一段时间发射的数据，时长和时间单位通过参数指定。
//        takeLast的这个重载方法默认在computation调度器上执行，也可以使用参数来指定其他的调度器。
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


        //==========================P131(Skip抑制Observable发射的前n项数据。)=================================
//        使用skip操作符，可以忽略Observable发射的前n项数据，只保留之后的数据。
        /*Observable.just(1, 2, 3, 4, 5)
                .skip(3)
                .subscribe(integer -> System.out.println("Next: " + integer),
                        throwable -> System.err.println("Error:" + throwable.getMessage()),
                        () -> System.out.println("Sequence complete."));*/

//        skip有一个重载方法能够接受一个时长而不是数量参数，它会丢弃原始Observable开始那段时间发射的数据，时长和时间单位通过参数指定。
        /*Observable.intervalRange(0, 10, 1, 1, TimeUnit.SECONDS)
                .skip(3, TimeUnit.SECONDS)
                .subscribe(aLong -> System.out.println("Next: " + aLong),
                        throwable -> System.err.println("Error:" + throwable.getMessage()),
                        () -> System.out.println("Sequence complete."));*/

        //==========================P133(SkipLast抑制Observable发射的后n项数据。)=================================
        /*Observable.just(1, 2, 3, 4, 5)
                .skipLast(3)
                .subscribe(integer -> System.out.println("Next: " + integer),
                        throwable -> System.err.println("Error:" + throwable.getMessage()),
                        () -> System.out.println("Sequence complete."));*/

//        同样，skipLast也有一个重载方法接受一个时长而不是数量参数，它会丢弃在原始Observable生命周期最后一段时间内发射的数据，时长和时间单位通过参数指定。
//        这个重载的方法默认在computation调度器上执行，也可以使用参数来指定其他调度器。
        /*Observable.intervalRange(0, 10, 1, 1, TimeUnit.SECONDS)
                .skip(3, TimeUnit.SECONDS)
                .subscribe(aLong -> System.out.println("Next: " + aLong),
                        throwable -> System.err.println("Error:" + throwable.getMessage()),
                        () -> System.out.println("Sequence complete."));*/

        //==========================P135(ElementAt只发射第n项数据。)=================================
//        elementAt操作符获取原始Observable发射的数据序列指定索引位置的数据项，然后当作自己的唯一数据发射。
//        elementAt(index)返回一个Maybe类型。
        /*Observable.just(1, 2, 3, 4, 5)
                .elementAt(2)
                .subscribe(integer -> System.out.println("Next: " + integer),
                        throwable -> System.out.println("Error: " + throwable.getMessage()),
                        () -> System.out.println("Sequence complete."));*/

//        如果原始Observable的数据项数小于index+1，那么会调用onComplete()方法（在RxJava1.x中也会抛出一个IndexOutOfBoundsException异常）。
//        所以，elementAt还通过了一个带默认值的方法，它返回一个Single类型。
        /*Observable.just(1, 2, 3, 4, 5)
                .elementAt(10, 0)
                .subscribe(integer -> System.out.println("Next: " + integer),
                        throwable -> System.out.println("Error: " + throwable.getMessage()));*/

//        跟first、last操作符类似，element还有一个elementOrError操作符，它表示要么能取到指定索引位置的数据，要么执行onError方法，也是返回Single类型。

        //==========================P138(ignoreElements不发射任何数据，只发射Observable的终止通知。)=================================
//        ignoreElements操作符抑制原始Observable发射的所有数据，只允许它的终止通知（onError或onComplete）通知，它返回一个Completable类型。
        /*Observable.just(1, 2, 3, 4, 5)
                .ignoreElements()
                .subscribe(() -> System.out.println("Sequence complete."),
                        throwable -> System.out.println("Error: " + throwable.getMessage()));*/

        //==========================P139(distinct过滤掉重复的数据项，只允许还没有发射的数据项通过。)=================================
        // (distinct还能接受一个Function作为参数，这个函数根据原始Observable发射的数据项产生一个Key，比较这些Key而不是数据本身，来判定两个数据是否不同。)
        /*Observable.just(1, 2, 1, 2, 3, 4, 5, 5, 6)
                .distinct()
                .subscribe(integer -> System.out.println("Next: " + integer),
                        throwable -> System.out.println("Error: " + throwable.getMessage()),
                        () -> System.out.println("Sequence complete."));*/

        //==========================P140(与distinct类似的是distinctUntilChanged操作符，该操作符与distinct的区别是，它只判定一个数据和它的直接前驱是否不同。)=================================
        /*Observable.just(1, 2, 1, 2, 3, 4, 5, 5, 6)
                .distinctUntilChanged()
                .subscribe(integer -> System.out.println("Next: " + integer),
                        throwable -> System.out.println("Error: " + throwable.getMessage()),
                        () -> System.out.println("Sequence complete."));*/

        //==========================P141(filter只发射通过谓词测试的数据项。)=================================
//        filter操作符使用你指定的一个谓词函数测试数据项，只有通过测试的数据才会被发射。
        /*Observable.just(2, 30, 2, 22, 5, 60, 1)
                .filter(integer -> integer > 10)
                .subscribe(integer -> System.out.println("Next: " + integer),
                        throwable -> System.out.println("Error: " + throwable.getMessage()),
                        () -> System.out.println("Sequence complete."));*/

        //==========================P142(debounce仅在过了一段指定的时间还没发射数据时才发射一个数据。)=================================
        /*Observable.create((ObservableOnSubscribe<Integer>) emitter -> {
            if (emitter.isDisposed()) return;
            for (int i = 1; i <= 20; i++) {
                emitter.onNext(i); //发射数据
                Thread.sleep(i * 100);
            }
            emitter.onComplete();
        }).debounce(500, TimeUnit.MILLISECONDS)
                .subscribe(integer -> System.out.println("Next: " + integer),
                        throwable -> System.out.println("Error: " + throwable.getMessage()),
                        () -> System.out.println("Sequence complete."));*/

//        debounce还有另外一种形式，即使用一个Function函数来限制发送的数据。
//        跟debounce类似的是throttleWithTimeout操作符，它与只使用时间参数来限流的debounce的功能相同。
    }
}
