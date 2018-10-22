package com.renrenxin.chapter16;

/**
 * Created by zinclee123 on 2018/10/22.
 */

public class TestHighOrderFunction {

    public static int identify(int x) {
        return x;
    }

    public static int sum_integers(int a, int b) {
        int sum = 0;
        for(int i = a; i <= b; i++) {
            sum += identify(i);
        }
        return sum;
    }

    public static int square(int x) {
        return x * x;
    }

    public static int sum_square(int a, int b) {
        int sum = 0;
        for(int i = a; i <= b; i++) {
            sum += square(i);
        }
        return sum;
    }

    public static double cube(int x) {
        return x * x * x;
    }

    public static int sum_cubes(int a, int b) {
        int sum = 0;
        for(int i = a; i <= b; i++) {
            sum += cube(i);
        }
        return sum;
    }
}
