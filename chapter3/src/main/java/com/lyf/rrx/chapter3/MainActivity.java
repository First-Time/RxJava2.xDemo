package com.lyf.rrx.chapter3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Action;
import io.reactivex.functions.BooleanSupplier;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //==========================P65（使用一个函数从头开始创建一个Observable，给这个操作符传递一个接受观察者作为参数的函数，编写这个函数让它的行为表现为一个Observable——恰当地调用观察者的onNext，onError，onComplete方法。）=================================
        /*Observable.create((ObservableOnSubscribe<Integer>) emitter -> {
            if (!emitter.isDisposed()) {
                for (int i = 0; i < 10; i++) {
                    emitter.onNext(i);
                }
                emitter.onComplete();
            }
        }).subscribe(integer -> System.out.println(integer),
                throwable -> System.out.println("Error: " + throwable.getMessage()),
                () -> System.out.println("Sequence complete."));*/

        //==========================P67（创建一个发射指定值的Observable。）=================================
//        just将单个数据转换为发射这个单个数据的Observable。
//        Observable.just("hello just").subscribe(s -> System.out.println(s));

//        just类似于from，但是from会将数组活Iterable的数据取出然后逐个发射，而just只是简单地原样发射，将数组或Iterable当成单个数据发射。
//        在RxJava2.0中，如果在just()中传入null，则会抛出一个空指针异常。
        /*Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .subscribe(integer -> System.out.println("Next: " + integer),
                        throwable -> System.out.println("Error: " + throwable.getMessage()),
                        () -> System.out.println("Sequence complete."));*/

        //==========================P69（from可以将其他种类的对象和数据转换为Observable。）=================================
//        在RxJava中，from操作符可以将Future、Iterable和数组转换成Observable，对于Iterable和数组，产生的Observable会发射Iterable或数组的每一项数据。
//        Observable.fromArray("hello", "from").subscribe(s -> System.out.println(s));

        //==========================P69=================================
        /*List<Integer> items = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            items.add(i);
        }

        Observable.fromIterable(items)
                .subscribe(integer -> System.out.println("Next: " + integer),
                        throwable -> System.out.println("Error: " + throwable.getMessage()),
                        () -> System.out.println("Sequence complete."));*/

        //==========================P70（对于Future，它会发射Future.get()方法返回的单个数据。）=================================
        /*ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> future = executorService.submit(new MyCallable());

        Observable.fromFuture(future).subscribe(s -> System.out.println(s));*/

        //==========================P71（from方法有一个可以接受两个可选参数的版本，分别指定超时时长和时间单位。如果过了指定的时长，Future还没有返回一个值，那么这个Observable就会发射错误通知并终止）=================================
        /*ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> future = executorService.submit(new MyCallable());

        Observable.fromFuture(future, 4, TimeUnit.SECONDS).subscribe(s -> System.out.println(s));*/

        //==========================P72（repeat创建一个发射特定数据重复次数的Observable。）=================================
//        repeat会重复地发射数据。某些实现允许我们重复发射某些数据序列，还有一些允许我们限制重复的次数。
//        repeat不是创建一个Observable，而是重复发射原始Observable的数据序列，这个序列或者是无限的，或者是通过repeat(n)指定的重复次数。
        /*Observable.just("hello repeat")
                .repeat(3)
                .subscribe(s -> System.out.println("s=" + s));*/

        //==========================P73（repeatWhen不是缓存和重放原始Observable的数据序列，而是有条件地重新订阅和发射原来的Observable。）=================================
//        将原始Observable的终止通知（完成或错误）当做一个void数据传递给一个通知处理器，以此来决定是否要重新订阅和发射原来的Observable。
//        这个通知处理器就像一个Observable操作符，接受一个发射void通知的Observable作为输入，返回一个发射void数据（意思是，重新订阅或发射原始Observable）或者终止通知（即使用repeatWhen终止发射数据）的Observable。
        /*Observable.range(0, 9)
                .repeatWhen(objectObservable -> Observable.timer(10, TimeUnit.SECONDS))
                .subscribe(integer -> System.out.println(integer));*/

        //==========================P74（repeatUntil是RxJava2.x新增的操作符，表示直到某个条件就不再发射数据，当BooleanSupplier的getAsBoolean()返回false时，表示重复发射上游的Observable；当getAsBoolean()返回true时，表示中止发射上游的Observable。）=================================
        /*final long startTimeMillis = System.currentTimeMillis();
        Observable.interval(500, TimeUnit.MILLISECONDS)
                .take(5)
                .repeatUntil(new BooleanSupplier() {
                    @Override
                    public boolean getAsBoolean() throws Exception {
                        return System.currentTimeMillis() - startTimeMillis > 5000;
                    }
                })
                .subscribe(aLong -> System.out.println(aLong));*/

        //==========================P76（defer：直到有观察者订阅时才创建Observable，并且为每个观察者创建一个全新的Observable。）=================================
//        defer操作符会一直等待直到有观察者订阅它，然后它使用Observable工厂方法生成一个Observable。它对每个观察者都这样做，因此尽管每个订阅者都以为自己订阅的是同一个Observable，但事实上每个订阅者获取的是它们自己单独的数据序列。
        /*Observable observable = Observable.defer(() -> Observable.just("hello defer"));
        observable.subscribe(s -> System.out.println(s));*/

        //==========================P78（interval：创建一个按固定时间间隔发射证书序列的Observable。）=================================
//        interval操作符返回一个Observable，它按固定时间间隔发射一个逐渐递增的证书序列。
//        interval接受一个表示时间间隔的参数和一个表示时间单位的参数，interval默认在computation调度器上执行。
//        Observable.interval(1, TimeUnit.SECONDS).take(10).subscribe(aLong -> System.out.println(aLong));

        //==========================P79（timer：创建一个Observable，它在一个给定的延迟后发射特殊的值。）=================================
//        timer返回一个Observable，它在延迟一段给定的时间后发射一个简单的数字0。timer操作符默认在computation调度器上执行。
//        Observable.timer(5, TimeUnit.SECONDS).subscribe(aLong -> System.out.println("hello timer"));
    }
}
