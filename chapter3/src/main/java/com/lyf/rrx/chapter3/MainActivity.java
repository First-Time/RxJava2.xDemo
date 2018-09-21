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

        //==========================P65=================================
        /*Observable.create((ObservableOnSubscribe<Integer>) emitter -> {
            if (!emitter.isDisposed()) {
                for (int i = 0; i < 10; i++) {
                    emitter.onNext(i);
                }
            }
        }).subscribe(integer -> System.out.println(integer),
                throwable -> System.out.println("Error: " + throwable.getMessage()),
                () -> System.out.println("Sequence complete."));*/

        //==========================P67=================================
//        Observable.just("hello just").subscribe(s -> System.out.println(s));

        /*Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .subscribe(integer -> System.out.println("Next: " + integer),
                        throwable -> System.out.println("Error: " + throwable.getMessage()),
                        () -> System.out.println("Sequence complete."));*/

        //==========================P69=================================
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

        //==========================P70=================================
        /*ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> future = executorService.submit(new MyCallable());

        Observable.fromFuture(future).subscribe(s -> System.out.println(s));*/

        //==========================P71=================================
        /*ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> future = executorService.submit(new MyCallable());

        Observable.fromFuture(future, 4, TimeUnit.SECONDS).subscribe(s -> System.out.println(s));*/

        //==========================P72=================================
        /*Observable.just("hello repeat")
                .repeat(3)
                .subscribe(s -> System.out.println("s=" + s));*/

        //==========================P73=================================
        /*Observable.range(0,9).repeatWhen(objectObservable -> Observable.timer(10, TimeUnit.SECONDS))
                .subscribe(integer -> System.out.println(integer));*/

        //==========================P74=================================
        /*final long startTimeMillis = System.currentTimeMillis();
        Observable.interval(500, TimeUnit.MILLISECONDS)
                .take(5)
                .repeatUntil(() -> System.currentTimeMillis() - startTimeMillis > 5000)
                .subscribe(aLong -> System.out.println(aLong));*/

        //==========================P76=================================
        /*Observable observable = Observable.defer(() -> Observable.just("hello defer"));
        observable.subscribe(s -> System.out.println(s));*/

        //==========================P78=================================
//        Observable.interval(1, TimeUnit.SECONDS).take(10).subscribe(aLong -> System.out.println(aLong));

        //==========================P79=================================
        Observable.timer(5, TimeUnit.SECONDS).subscribe(aLong -> System.out.println("hello timer"));
    }
}
