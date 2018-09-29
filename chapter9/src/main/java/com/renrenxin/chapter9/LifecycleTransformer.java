package com.renrenxin.chapter9;

import com.safframework.lifecycle.LifecyclePublisher;

import org.reactivestreams.Publisher;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.CompletableTransformer;
import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.MaybeTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.SingleTransformer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.processors.BehaviorProcessor;

/**
 * Created by zinclee123 on 2018/9/27.
 */

public class LifecycleTransformer<T> implements ObservableTransformer<T, T>, FlowableTransformer<T, T>, SingleTransformer<T, T>, MaybeTransformer<T, T>, CompletableTransformer {

    private final BehaviorProcessor<Integer> lifecycleBehavior;

    private LifecycleTransformer() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    public LifecycleTransformer(BehaviorProcessor<Integer> lifecycleBehavior) {
        this.lifecycleBehavior = lifecycleBehavior;
    }

    @Override
    public CompletableSource apply(Completable upstream) {
        return upstream.ambWith(
                lifecycleBehavior.filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return integer == LifecyclePublisher.ON_DESTROY_VIEW ||
                                integer == LifecyclePublisher.ON_DESTROY ||
                                integer == LifecyclePublisher.ON_DETACH;
                    }
                }).take(1).flatMapCompletable(new Function<Integer, CompletableSource>() {
                    @Override
                    public CompletableSource apply(Integer integer) throws Exception {
                        return Completable.complete();
                    }
                })
        );
    }

    @Override
    public Publisher<T> apply(Flowable<T> upstream) {
        return upstream.takeUntil(lifecycleBehavior.skipWhile(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) throws Exception {
                return integer != LifecyclePublisher.ON_DESTROY_VIEW ||
                        integer != LifecyclePublisher.ON_DESTROY ||
                        integer != LifecyclePublisher.ON_DETACH;
            }
        }));
    }

    @Override
    public MaybeSource<T> apply(Maybe<T> upstream) {
        return upstream.takeUntil(lifecycleBehavior.skipWhile(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) throws Exception {
                return integer != LifecyclePublisher.ON_DESTROY_VIEW ||
                        integer != LifecyclePublisher.ON_DESTROY ||
                        integer != LifecyclePublisher.ON_DETACH;
            }
        }));
    }

    @Override
    public ObservableSource<T> apply(Observable<T> upstream) {
        return upstream.takeUntil(lifecycleBehavior.skipWhile(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return integer != LifecyclePublisher.ON_DESTROY_VIEW &&
                                integer != LifecyclePublisher.ON_DESTROY &&
                                integer != LifecyclePublisher.ON_DETACH;
                    }
                }).toObservable()
        );
    }

    @Override
    public SingleSource<T> apply(Single<T> upstream) {
        return upstream.takeUntil(lifecycleBehavior.skipWhile(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) throws Exception {
                return integer != LifecyclePublisher.ON_DESTROY_VIEW ||
                        integer != LifecyclePublisher.ON_DESTROY ||
                        integer != LifecyclePublisher.ON_DETACH;
            }
        }));
    }
}
