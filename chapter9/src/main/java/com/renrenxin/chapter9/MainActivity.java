package com.renrenxin.chapter9;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.RxLifecycleAndroid;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class MainActivity extends RxAppCompatActivity {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private Observable<Long> myObservable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //==========================P197=================================
        Observable.intervalRange(0, 25, 0, 1000, TimeUnit.MILLISECONDS)
                .compose(this.<Long>bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long integer) throws Exception {
                        System.out.println("OnNext: " + integer);
                    }
                });

//        myObservable = Observable.intervalRange(0, 25, 0, 1000, TimeUnit.MILLISECONDS);

        //==========================P199(transformer方法。)=================================
        /*Observable.just(123, 456)
                .compose(transformer())
                .subscribe(s -> System.out.println("s=" + s));*/

        //==========================P206(追踪RxJava的使用。)=================================
        /*Observable.just("tony", "cafei", "aaron")
                .compose(RxTrace.<String>logObservable("first", RxTrace.LOG_SUBSCRIBE | RxTrace.LOG_NEXT_DATA))
                .subscribe(s -> System.out.println("s=" + s));*/

        /*Observable.just("tony","cafei","aaron")
                .compose(RxTrace.<String>logObservable("first",RxTrace.LOG_SUBSCRIBE | RxTrace.LOG_NEXT_DATA))
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        return s.toUpperCase();
                    }
                })
                .compose(RxTrace.<String>logObservable("second", RxTrace.LOG_SUBSCRIBE | RxTrace.LOG_NEXT_DATA))
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println("s=" + s);
                    }
                });*/

        /*Observable.just("tony", "cafei", "aaron")
                .compose(RxTrace.<String>logObservable("first", RxTrace.LOG_SUBSCRIBE | RxTrace.LOG_NEXT_DATA))
                .map(s -> s.toUpperCase())
                .compose(RxTrace.<String>logObservable("second", RxTrace.LOG_NEXT_DATA))
//                .compose(RxJavaUtils.<String>observableToMain())
                .compose(RxTrace.<String>logObservable("third", RxTrace.LOG_COMPLETE | RxTrace.LOG_TERMINATE))
                .subscribe(s -> System.out.println("s=" + s));*/

    }

    @Override
    protected void onResume() {
        super.onResume();

        /*DisposableObserver<Integer> disposableObserver = new DisposableObserver<Integer>() {
            @Override
            public void onNext(Integer integer) {
                System.out.println("OnNext: " + integer);
            }

            @Override
            public void onError(Throwable e) {
                System.err.println(e.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("OnComplete.");
            }
        };

        myObservable.subscribe(disposableObserver);

        compositeDisposable.add(disposableObserver);*/
    }

    @Override
    protected void onStop() {
        super.onStop();

//        compositeDisposable.clear();
    }

    public static <String> ObservableTransformer<Integer, java.lang.String> transformer() {
        return new ObservableTransformer<Integer, java.lang.String>() {
            @Override
            public ObservableSource<java.lang.String> apply(Observable<Integer> upstream) {
                return upstream.map(new Function<Integer, java.lang.String>() {
                    @Override
                    public java.lang.String apply(Integer integer) throws Exception {
                        return java.lang.String.valueOf(integer);
                    }
                });
            }
        };
    }
}
