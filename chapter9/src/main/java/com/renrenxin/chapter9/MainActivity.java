package com.renrenxin.chapter9;

import android.os.Bundle;

import com.trello.rxlifecycle2.android.RxLifecycleAndroid;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;

public class MainActivity extends RxAppCompatActivity {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private Observable<Integer> myObservable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        RxLifecycle
//        myObservable = Observable.range(0, 25);

//        transformer
        /*Observable.just(123, 456)
                .compose(new ObservableTransformer<Integer, String>() {
                    @Override
                    public ObservableSource<String> apply(Observable<Integer> upstream) {
                        return upstream.map(new Function<Integer, java.lang.String>() {
                            @Override
                            public java.lang.String apply(Integer integer) throws Exception {
                                return java.lang.String.valueOf(integer);
                            }
                        });
                    }
                })
                .subscribe(s -> System.out.println("s=" + s));*/

        //==========================P206(追踪RxJava的使用。)=================================
        Observable.just("tony", "cafei", "aaron")
                .compose(RxTrace.<String>logObservable("first", RxTrace.LOG_SUBSCRIBE | RxTrace.LOG_NEXT_DATA))
                .map(s -> s.toUpperCase())
                .compose(RxTrace.<String>logObservable("second", RxTrace.LOG_NEXT_DATA))
//                .compose(RxJavaUtils.<String>observableToMain())
                .compose(RxTrace.<String>logObservable("third", RxTrace.LOG_COMPLETE | RxTrace.LOG_TERMINATE))
                .subscribe(s -> System.out.println("s=" + s));

    }

    /*public static <String> ObservableTransformer<Integer, java.lang.String> transformer() {
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
    }*/

    /*@Override
    protected void onResume() {
        super.onResume();
        *//*myObservable.compose(RxLifecycleAndroid.bindActivity(lifecycle()))
                .subscribe(integer -> System.out.println("OnNext: " + integer));*//*

        DisposableObserver<Integer> disposableObserver = new DisposableObserver<Integer>() {
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

        compositeDisposable.add(disposableObserver);
    }

    @Override
    protected void onStop() {
        super.onStop();
        compositeDisposable.clear();
    }*/
}
