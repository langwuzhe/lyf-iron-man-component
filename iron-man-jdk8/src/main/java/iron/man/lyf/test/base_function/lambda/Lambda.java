package iron.man.lyf.test.base_function.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * 在将函数作为⼀一等公⺠民的语⾔言中，Lambda表达式 的类型是函数。如 python
 * 但在Java中，Lambda表达式是对 象，他们必须依附于⼀一类特别的对象类型——函 数式接⼝口(functional interface)
 *
 *
 *
 * @author liuyanfei
 * @description
 * @date 2022/2/4 11:55 上午
 **/
public class Lambda {


    @Test
    public void test1(){
        /**
         * 零、概述
         */
//        1.这个 lambda 表达式 其实是对MyInterface 函数的实现
//        2.只有函数式接口才可以这么写，如果MyInterface 不是函数式接口，不可以这么写
        MyInterface myInterface = () -> System.out.println("123");
        myInterface.test();


        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);


        /**
         * 一、--------------------------  forEach
         */
        //按匿名内部类的方式写！ Consumer 是一个函数式接口
        list.forEach(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println(integer);
            }
        });
        //lambda 表达式
        //参数 -> 方法
        list.forEach((Integer i) -> {
            System.out.println(i);
        });

        //再简化
        //参数：参数可以由编译器推断类型，可以省略类型。如果省略类型，且参数只有一个可以省略小括号
        //方法：如果方法只有一句话，可以省略 {}
        list.forEach(i -> System.out.println(i));


        //究级简化
        //方法引用，与上面的效果是一样的
        list.forEach(System.out::println);


        /**
         * 二、--------------------------  map
         * forEach 是用的Consumer循环参数
         *  map 是用的 Function 循环参数
         *
         * Function 和 Consumer 一样都是函数式接口。
         *
         * Consumer 是没有返回值的。所以用了forEach之后就不能再操作了
         * Function 是有返回值的。所以用了
         *
         *
         * map也是循环功能，但是有返回值
         */

        //1. 内部类写法
        List<String> list2 = Arrays.asList("1", "2", "3");

//
//          public interface Function<T, R> {
//
//
//               R apply (T t);
//
//           }
//
//           根据Function 函数可以看出 T 是输入参数类型，R 是输出参数类型
//           所以下面的 String 是输入的参数类型，Integer 是输出参数类型
        list2.stream().map(new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return Integer.valueOf(s);
            }
        }).forEach(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println(integer);
            }
        });


        //2.lambda 写法
        //可以看到  map 是需要且必须、有返回值的，forEach 不需要返回值。所以 forEach 后面不能再跟任何表达式了
        list2.stream().map(s -> {
            return Integer.valueOf(s);
        }).forEach(integer -> {
            System.out.println(integer);
        });



        //3.lambda  最终写法
        // map 后的函数  去掉 大括号，可以不写 return。
        list2.stream().map(s -> Integer.valueOf(s)).forEach(System.out::println);
    }
}
