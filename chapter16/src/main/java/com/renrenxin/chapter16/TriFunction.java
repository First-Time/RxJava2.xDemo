package com.renrenxin.chapter16;

/**
 * Created by zinclee123 on 2018/10/22.
 */

public interface TriFunction<U, T, S, R> {

    /**
     * Applys this function to the given arguments.
     * @param u
     * @param t
     * @param s
     * @return the function result
     */
    R apply (U u, T t, S s);
}
