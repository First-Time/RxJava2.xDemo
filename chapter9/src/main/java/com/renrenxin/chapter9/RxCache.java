package com.renrenxin.chapter9;

import android.support.constraint.solver.Cache;

import org.reactivestreams.Publisher;

import java.io.Serializable;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.functions.Function;

/**
 * Created by zinclee123 on 2018/9/27.
 */

public class RxCache {

    public static <T> FlowableTransformer<T, T> transformer(final String key, final Cache cache) {
        return new FlowableTransformer<T, T>() {
            @Override
            public Publisher<T> apply(Flowable<T> upstream) {
                return upstream.map(new Function<T, T>() {
                    @Override
                    public T apply(T t) throws Exception {
//                        cache.put(key, (Serializable) t);
                        return t;
                    }
                });
            }
        };
    }
}
