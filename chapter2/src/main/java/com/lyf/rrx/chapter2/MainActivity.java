package com.lyf.rrx.chapter2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.reactivestreams.Processor;
import org.reactivestreams.Subscriber;

import java.util.concurrent.TimeUnit;

import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeOnSubscribe;
import io.reactivex.Notification;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.AsyncSubject;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.ReplaySubject;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.text_view);

        //==========================P11=================================
        /*Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("Hello World!");
            }
        });

        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("Hello World!");
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println(s);
            }
        });

        Observable.just("Hello World!").subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println(s);
            }
        });*/

        //==========================P15=================================
        /*Observable.just("Hello World!")
                .subscribe(s -> System.out.println(s),
                        throwable -> System.out.println(throwable.getMessage()),
                        () -> System.out.println("onComplete"),
                        disposable -> System.out.println("subscribe"));*/

        //==========================P16=================================
        /*Observable.just("Hello World").subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("onSubscribe");
            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println(e.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });*/

        //==========================P17=================================
        /*Disposable disposable = Observable.just("Hello")
                .doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println("doOnNext:" + s);
                    }
                })
                .doAfterNext(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println("doAfterNext:" + s);
                    }
                })
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println("doOnComplete:");
                    }
                })
//                订阅之后回调的方法
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        System.out.println("doOnSubscribe:");
                    }
                })
                .doAfterTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println("doAfterTerminate:");
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println("doFinally:");
                    }
                })
//                Observable每发射一个数据就会触发这个回调，不仅包括onNext，还包括onError，onCompleted
                .doOnEach(new Consumer<Notification<String>>() {
                    @Override
                    public void accept(Notification<String> stringNotification) throws Exception {
                        System.out.println("doOnEach:" + (stringNotification.isOnNext() ? "onNext" : stringNotification.isOnComplete() ? "onComplete" : "onError"));
                    }
                })
//                订阅后可以取消订阅
                .doOnLifecycle(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        System.out.println("doOnLifecycle:" + disposable.isDisposed());
//                        disposable.dispose();
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println("doOnLifecycle run:");
                    }
                })

                .doOnTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println("doOnTerminate:");
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println("收到消息：" + s);
                    }
                });*/

        //==========================P20(Cold Observable)=================================
        /*Consumer<Long> subscriber1 = aLong -> System.out.println("subscriber1:" + aLong);
        Consumer<Long> subscriber2 = aLong -> System.out.println("subscriber2:" + aLong);

        Observable<Long> observable = Observable.create((ObservableOnSubscribe<Long>) emitter -> Observable.interval(10, TimeUnit.MILLISECONDS, Schedulers.computation())
                .take(Integer.MAX_VALUE)
                .subscribe(emitter::onNext))
                .observeOn(Schedulers.newThread());

        observable.subscribe(subscriber1);
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        observable.subscribe(subscriber2);*/

        //==========================P22(Cold Observable 如何转换成Hot Observable)=================================
        /*Consumer<Long> subscriber1 = aLong -> System.out.println("subscriber1:" + aLong);
        Consumer<Long> subscriber2 = aLong -> System.out.println("subscriber2:" + aLong);
        Consumer<Long> subscriber3 = aLong -> System.out.println("subscriber3:" + aLong);

        ConnectableObservable<Long> observable = Observable.create((ObservableOnSubscribe<Long>) emitter -> Observable.interval(10, TimeUnit.MILLISECONDS, Schedulers.computation())
                .take(Integer.MAX_VALUE)
                .subscribe(emitter::onNext))
                .observeOn(Schedulers.newThread())
                .publish();

        observable.subscribe(subscriber1);
        observable.subscribe(subscriber2);
        observable.connect();

        try {
            Thread.sleep(20L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        observable.subscribe(subscriber3);

        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        //==========================P25=================================
        /*Consumer<Long> subscriber1 = aLong -> System.out.println("subscriber1:" + aLong);
        Consumer<Long> subscriber2 = aLong -> System.out.println("subscriber2:" + aLong);
        Consumer<Long> subscriber3 = aLong -> System.out.println("subscriber3:" + aLong);

        Observable<Long> observable = Observable.create((ObservableOnSubscribe<Long>) emitter -> Observable.interval(10, TimeUnit.MILLISECONDS, Schedulers.computation())
                .take(Integer.MAX_VALUE)
                .subscribe(emitter::onNext))
                .observeOn(Schedulers.newThread());

        PublishSubject<Long> subject = PublishSubject.create();
        observable.subscribe(subject);

        subject.subscribe(subscriber1);
        subject.subscribe(subscriber2);

        try {
            Thread.sleep(20L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        subject.subscribe(subscriber3);
*/
        //==========================P29(Hot Observable 如何转换成 Cold Observable)=================================
        /*Consumer<Long> subscriber1 = aLong -> System.out.println("subscriber1:" + aLong);
        Consumer<Long> subscriber2 = aLong -> System.out.println("subscriber2:" + aLong);

        ConnectableObservable<Long> connectableObservable = Observable.create((ObservableOnSubscribe<Long>) emitter -> Observable.interval(10, TimeUnit.MILLISECONDS, Schedulers.computation())
                .take(Integer.MAX_VALUE)
                .subscribe(emitter::onNext))
                .observeOn(Schedulers.newThread()).publish();
//        connectableObservable.connect();

        Observable<Long> observable = connectableObservable.refCount();

        Disposable disposable1 = observable.subscribe(subscriber1);
        Disposable disposable2 = observable.subscribe(subscriber2);

        try {
            Thread.sleep(20L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        disposable1.dispose();
        disposable2.dispose();

        System.out.println("重新开始数据流");

        observable.subscribe(subscriber1);
        observable.subscribe(subscriber2);*/

        //==========================P30=================================
        /*Consumer<Long> subscriber1 = aLong -> System.out.println("subscriber1:" + aLong);
        Consumer<Long> subscriber2 = aLong -> System.out.println("subscriber2:" + aLong);
        Consumer<Long> subscriber3 = aLong -> System.out.println("subscriber3:" + aLong);

        Observable<Long> observable = Observable.create((ObservableOnSubscribe<Long>) emitter -> Observable.interval(10, TimeUnit.MILLISECONDS, Schedulers.computation())
                .take(Integer.MAX_VALUE)
                .subscribe(emitter::onNext))
                .observeOn(Schedulers.newThread())
                .share();

//        Observable<Long> observable = connectableObservable.share();

        Disposable disposable1 = observable.subscribe(subscriber1);
        Disposable disposable2 = observable.subscribe(subscriber2);
        observable.subscribe(subscriber3);

        try {
            Thread.sleep(20L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        disposable1.dispose();
        disposable2.dispose();

        System.out.println("subscriber1、subscriber2 重新订阅");

        observable.subscribe(subscriber1);
        observable.subscribe(subscriber2);*/

        //==========================P36(Single之后onSuccess和onError事件。)=================================
        /*Single.create((SingleOnSubscribe<String>) emitter -> emitter.onSuccess("test"))
                .subscribe(s -> System.out.println(s), throwable -> throwable.printStackTrace());

        Single.create((SingleOnSubscribe<String>) emitter -> emitter.onSuccess("test"))
                .subscribe((s, throwable) -> System.out.println(s));*/

        //==========================P39(Completable在创建后，不会发射任何数据，从CompletableEmitter的源码中可以看到。Completable之后onComplete和onError事件，同事Completable并没有map、flatMap等操作符。)=================================
        /*Completable.fromAction(() -> System.out.println("Hello World")).subscribe();

        Completable.create(emitter -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                emitter.onComplete();
            } catch (InterruptedException e) {
                emitter.onError(e);
            }
        }).andThen(Observable.range(1, 10))
        .subscribe(integer -> System.out.println(integer));*/

        //==========================P43(Maybe是RxJava2.x之后才有的新类型，可以看成是Single和Completable的结合。)=================================
        /*Maybe.create((MaybeOnSubscribe<String>) emitter -> {
            emitter.onSuccess("testA");
            emitter.onSuccess("testB");
        }).subscribe(s -> System.out.println("s=" + s));*/

        /*Maybe.create((MaybeOnSubscribe<String>) emitter -> {
            emitter.onComplete();
            emitter.onSuccess("testA");
        }).subscribe(s -> System.out.println("s=" + s));

        Maybe.create((MaybeOnSubscribe<String>) emitter -> {
            emitter.onComplete();
            emitter.onSuccess("testA");
        }).subscribe(s -> System.out.println("s=" + s),
                throwable -> {
                },
                () -> System.out.println("Maybe onComplete"));*/

        //==========================P49(Observer会接收AsyncSubject的onComplete之前的最后一个数据)=================================
        /*AsyncSubject<String> subject = AsyncSubject.create();
        subject.onNext("asyncSubject1");
        subject.onNext("asyncSubject2");
        subject.onComplete();

        subject.subscribe(s -> System.out.println("asyncSubject:" + s),
                throwable -> System.out.println("asyncSubject onError"),
                () -> System.out.println("asyncSubject:complete"));
        subject.onNext("asyncSubject3");
        subject.onNext("asyncSubject4");*/

        //==========================P50(Observer会先接收到BehaviorSubject被订阅之前的最后一个数据，再接收订阅之后发射过来的数据。如果BehaviorSubject被订阅之前没有发送任何数据，则会发送一个默认数据)=================================
        /*BehaviorSubject<String> subject = BehaviorSubject.createDefault("behaviorSubject1");
//        BehaviorSubject<String> subject = BehaviorSubject.create();

        subject.onNext("behaviorSubject2");

        subject.subscribe(s -> System.out.println("behaviorSubject:" + s),
                throwable -> System.out.println("behaviorSubject onError"),
                () -> System.out.println("behaviorSubject:complete"));

        subject.onNext("behaviorSubject3");
        subject.onNext("behaviorSubject4");*/

        //==========================P52(ReplaySubject会发射所有来自原始Observable的数据给观察者，无论它们是合适订阅的。)=================================
        /*ReplaySubject<String> subject = ReplaySubject.create();
//        ReplaySubject<String> subject = ReplaySubject.createWithSize(1);
        subject.onNext("replaySubject1");
        subject.onNext("replaySubject2");

        subject.subscribe(s -> System.out.println("replaySubject:" + s),
                throwable -> System.out.println("replaySubject onError"),
                () -> System.out.println("replaySubject onComplete"));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        subject.subscribe(s -> System.out.println("replaySubject2:" + s),
                throwable -> System.out.println("replaySubject2 onError"),
                () -> System.out.println("replaySubject2 onComplete"));

        subject.onNext("replaySubject3");
        subject.onNext("replaySubject4");*/

        //==========================P54(Observer只接收PublishSubject被订阅之后发送的数据)=================================
        /*PublishSubject<String> subject = PublishSubject.create();

        subject.onNext("publishSubject1");
        subject.onNext("publishSubject2");

        subject.subscribe(s -> System.out.println("publishSubject:" + s),
                throwable -> System.out.println("publishSubject onError"),
                () -> System.out.println("publishSubject onComplete"));

        subject.onNext("publishSubject3");
        subject.onNext("publishSubject4");
        subject.onComplete();*/

        //==========================P56=================================
        /*PublishSubject<String> subject = PublishSubject.create();
        *//*Observable subject = Observable.create((ObservableOnSubscribe<String>) emitter -> {
            emitter.onNext("Foo " + Thread.currentThread().getName());
            emitter.onNext("Bar " + Thread.currentThread().getName());
            emitter.onComplete();
        }).subscribeOn(Schedulers.io());*//*

        subject.subscribe(s -> System.out.println("publishSubject:" + s + " " + Thread.currentThread().getName()),
                throwable -> System.out.println("publishSubject onError"),
                () -> System.out.println("publishSubject onComplete"));

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        subject.onNext("Foo " + Thread.currentThread().getName());
        subject.onNext("Bar " + Thread.currentThread().getName());
        subject.onComplete();*/
    }
}
