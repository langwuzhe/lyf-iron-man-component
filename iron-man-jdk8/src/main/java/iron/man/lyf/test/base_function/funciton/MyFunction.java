/*
 * Copyright (c) 2010, 2013, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
package iron.man.lyf.test.base_function.funciton;

import java.util.Objects;


@FunctionalInterface
public interface MyFunction<T, R> {

    R apply(T t);



    default <V> MyFunction<V, R> compose(MyFunction<? super V, ? extends T> before) {
        Objects.requireNonNull(before);


        //原谅我水平有限  只能把第二层弄出来，第一层实在写不出来


        //function2
        MyFunction<V, R> myFunction =  new MyFunction<V, R>() {
            @Override
            public R apply(V v) {
                System.out.println("第二层");
                System.out.println(v);
                return (R) before.apply(v);
            }
        };



        //function1
         return (V v) -> {
            return apply((T) myFunction.apply(v));
        };

//        return  v -> {return apply(before.apply(v));};
    }

    default <V> MyFunction<T, V> andThen(MyFunction<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return (T t) -> after.apply(apply(t));
    }

    static <T> MyFunction<T, T> identity() {
        return t -> t;
    }
}
