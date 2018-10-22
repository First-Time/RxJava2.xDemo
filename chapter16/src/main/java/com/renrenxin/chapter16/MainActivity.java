package com.renrenxin.chapter16;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Arrays;
import java.util.BitSet;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainActivity extends AppCompatActivity {

    @TargetApi(Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*System.out.println(TestHighOrderFunction.sum_integers(1, 10));
        System.out.println(TestHighOrderFunction.sum_square(1, 10));
        System.out.println(TestHighOrderFunction.sum_cubes(1, 10));*/

        /*Function identify = x -> x;
        Function square = x -> x * x;
        Function cube = x -> x * x * x;
        Function inc = x -> x + 1;

        System.out.println(sum(identify, 1, inc, 10));
        System.out.println(sum(square, 1, inc, 10));
        System.out.println(sum(cube, 1, inc, 10));*/

        User u1 = new User("tony");
        User u2 = new User("cafei");
        User u3 = new User("aaron");

        List<User> users = Arrays.asList(u1, u2, u3);
        Stream<User> userStream = users.stream();
        Random random = new Random();
        random.ints();

        BitSet bitSet = new BitSet();
        bitSet.stream();

        Pattern pattern = Pattern.compile("");
        pattern.splitAsStream("");


        /*Collections.sort(users, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });*/

//        Collections.sort(users, (o1, o2) -> o1.getName().compareTo(o2.getName()));

        /*Collections.sort(users, Comparator.comparing(new java.util.function.Function<User, String>() {
            @Override
            public String apply(User user) {
                return user.getName();
            }
        }));*/
//        Collections.sort(users, Comparator.comparing(user -> user.getName()));
        Collections.sort(users, Comparator.comparing(User::getName));


        /*Supplier<User> userSupplier = User::new;
        userSupplier.get().setName("刘燕霏");
        User user1 = userSupplier.get();
        System.out.println(user1.getName());*/

            /*users.forEach(new Consumer<User>() {
                @Override
                public void accept(User user) {
                    System.out.println(user.getName());
                }
            });*/

        users.forEach(user -> System.out.println(user.getName()));

        //Stream
        java.util.function.Function<Integer, java.util.function.Function<Integer, java.util.function.Function<Integer, Integer>>> currying = x -> y -> z -> (x + y) * z;

        java.util.function.Function<Integer, java.util.function.Function<Integer, java.util.function.Function<Integer, Integer>>> currying2 =
                new java.util.function.Function<Integer, java.util.function.Function<Integer, java.util.function.Function<Integer, Integer>>>() {
                    @Override
                    public java.util.function.Function<Integer, java.util.function.Function<Integer, Integer>> apply(Integer x) {
                        return new java.util.function.Function<Integer, java.util.function.Function<Integer, Integer>>() {
                            @Override
                            public java.util.function.Function<Integer, Integer> apply(Integer y) {
                                return new java.util.function.Function<Integer, Integer>() {
                                    @Override
                                    public Integer apply(Integer z) {
                                        return (x + y) * z;
                                    }
                                };
                            }
                        };
                    }
                };
        System.out.println(currying2.apply(4).apply(5).apply(6));


        IntFunction<IntFunction<IntUnaryOperator>> f = x -> y -> z -> (x + y) * z;
        IntFunction<IntFunction<IntUnaryOperator>> f2 = new IntFunction<IntFunction<IntUnaryOperator>>() {
            @Override
            public IntFunction<IntUnaryOperator> apply(int x) {
                return new IntFunction<IntUnaryOperator>() {
                    @Override
                    public IntUnaryOperator apply(int y) {
                        return new IntUnaryOperator() {
                            @Override
                            public int applyAsInt(int z) {
                                return (x + y) * z;
                            }
                        };
                    }
                };
            }
        };
        System.out.println(f.apply(4).apply(5).applyAsInt(6));

        TriFunction<Integer, Integer, Integer, Integer> triFunction = (x, y, z) -> (x + y) * z;
        System.out.println(triFunction.apply(4, 5, 6));

        //CompletableFuture
        /*ExecutorService executor = Executors.newCachedThreadPool();
        Future<String> future = executor.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("running task");
                Thread.sleep(10000);
                return "return task";
            }
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("do something else"); //前面的Callable在其他线程中运行着，可以做一些其他的事情

        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }*/


        /*CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> System.out.println("Hello"));
        try {
            future1.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "World");
        try {
            System.out.println(future2.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("CompletableFuture");*/

        /*CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "Hello");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        future2.complete("World");
        try {
            System.out.println(future2.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("CompletableFuture");*/


        /*CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "Hello");
        future2.completeExceptionally(new Exception());
        try {
            System.out.println(future2.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("CompletableFuture");*/

        /*CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "Hello");
        future2 = future2.thenApply(s -> s + "World")
                .thenApply(s -> s.toUpperCase());
        try {
            System.out.println(future2.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }*/


        /*CompletableFuture<Double> future3 = CompletableFuture.supplyAsync(() -> "10")
                .thenApply(Integer::parseInt)
                .thenApply(i -> i * 10.0);
        try {
            System.out.println(future3.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }*/

        /*CompletableFuture<String> future4 = CompletableFuture.supplyAsync(() -> "Hello")
                .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + "World"));*/

        /*CompletableFuture<Double> future4 = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                return "100";
            }
        })
                .thenCompose(new java.util.function.Function<String, CompletionStage<String>>() {
                    @Override
                    public CompletionStage<String> apply(String s) {
                        return CompletableFuture.supplyAsync(new Supplier<String>() {
                            @Override
                            public String get() {
                                return s + "100";
                            }
                        });
                    }
                })
                .thenCompose(new java.util.function.Function<String, CompletionStage<Double>>() {
                    @Override
                    public CompletionStage<Double> apply(String s) {
                        return CompletableFuture.supplyAsync(new Supplier<Double>() {
                            @Override
                            public Double get() {
                                return Double.parseDouble(s);
                            }
                        });
                    }
                });
        try {
            System.out.println(future4.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }*/


        //组合
        /*CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "100");
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> 100);
//        CompletableFuture<Double> future = future1.thenCombine(future2, (s, integer) -> Double.parseDouble(s + integer));
        CompletableFuture<Void> future = future1.thenAcceptBoth(future2, (s, integer) -> System.out.println(Double.parseDouble(s + integer)));
        try {
            future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }*/

        //计算结果完成时的处理
        /*CompletableFuture.supplyAsync(() -> "Hello")
                .thenApply(new java.util.function.Function<String, String>() {
                    @Override
                    public String apply(String s) {
                        return s + "World";
                    }
                })
                .thenApply(s -> s + "\nThis is CompletableFuture demo")
                .thenApply(String::toLowerCase)
                .whenComplete(new BiConsumer<String, Throwable>() {
                    @Override
                    public void accept(String s, Throwable throwable) {
                        System.out.println(s);
                    }
                });*/

        //执行完Action可以做转换
        /*CompletableFuture<Double> future = CompletableFuture.supplyAsync(() -> "100")
                .thenApply(new java.util.function.Function<String, String>() {
                    @Override
                    public String apply(String s) {
                        return s + "100";
                    }
                })
                .handle(new BiFunction<String, Throwable, Double>() {
                    @Override
                    public Double apply(String s, Throwable throwable) {
                        return s != null ? Double.parseDouble(s) : 0;
                    }
                });
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }*/

        //纯消费（执行Action）
        /*CompletableFuture.supplyAsync(() -> "Hello")
                .thenApply(new java.util.function.Function<String, String>() {
                    @Override
                    public String apply(String s) {
                        return s + " World";
                    }
                })
                .thenApply(s -> s + "\nThis is CompletableFuture demo")
                .thenApply(String::toLowerCase)
                .thenAccept(System.out::println);*/

        //Either
        /*Random random1 = new Random();
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(random1.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "from future1";
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(random1.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "from future2";
        });
        CompletableFuture<Void> future = future1.acceptEither(future2, new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("The future is " + s);
            }
        });
        try {
            future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }*/

        //acceptToEither
        /*Random random1 = new Random();
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(random1.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "from future1";
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(random1.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "from future2";
        });
        CompletableFuture<String> future = future1.applyToEither(future2, new java.util.function.Function<String, String>() {
            @Override
            public String apply(String s) {
                return "The future is " + s;
            }
        });
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }*/

        //其它方法
        /*CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "tony");
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "cafei");
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> "aaron");
        CompletableFuture.allOf(future1, future2, future3)
                .thenApply(new java.util.function.Function<Void, String>() {
                    @Override
                    public String apply(Void v) {
                        return Stream.of(future1, future2, future3)
                                .map(new java.util.function.Function<CompletableFuture<String>, String>() {
                                    @Override
                                    public String apply(CompletableFuture<String> stringCompletableFuture) {
                                        return stringCompletableFuture.join();
                                    }
                                })
                                .collect(Collectors.joining(" "));
                    }
                })
                .thenAccept(new Consumer<String>() {
                    @Override
                    public void accept(String s) {
                        System.out.println(s);
                    }
                });*/

        /*Random random1 = new Random();
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(random1.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "from future1";
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(random1.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "from future2";
        });
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(random1.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "from future3";
        });
        CompletableFuture<Object> future = CompletableFuture.anyOf(future1, future2, future3);
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }*/

        //CompletableFuture异常处理
        /*CompletableFuture.supplyAsync(() -> "hello world")
                .thenApply(s -> {
//                    s = null;
                    int length = s.length();
                    return length;
                }).thenAccept(i -> System.out.println(i))
                .exceptionally(new java.util.function.Function<Throwable, Void>() {
                    @Override
                    public Void apply(Throwable t) {
                        System.out.println("Unexpected error:" + t);
                        return null;
                    }
                });*/

        /*CompletableFuture.supplyAsync(() -> "hello world")
                .thenApply(s -> {
                    s = null;
                    int length = s.length();
                    return length;
                }).thenAccept(i -> System.out.println(i))
                .whenComplete(new BiConsumer<Void, Throwable>() {
                    @Override
                    public void accept(Void aVoid, Throwable throwable) {
                        if (throwable != null) {
                            System.out.println("Unexpected error:" + throwable);
                        } else {
                            System.out.println(aVoid);
                        }
                    }
                });*/
    }

    /*public static int sum(Function term, int a, int b) {
        int sum = 0;
        for(int i = a; i <= b; i++) {
            sum += term.opera(i);
        }
        return sum;
    }*/

    public static int sum(Function term, int a, Function next, int b) {
        if (a > b) {
            return 0;
        } else {
            return term.opera(a) + sum(term, next.opera(a), next, b);
        }
    }
}
