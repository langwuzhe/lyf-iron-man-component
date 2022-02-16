package iron.man.lyf.test.steam;

import org.junit.Test;

import java.util.*;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 *
 * 流由三部分组成：
 *  1.源
 *  2.零个或者多个中间部分
 *  3.终止部分
 *
 *
 *
 * 流操作的分类：
 *  1.惰性求值
 *  2.及早求值
 *
 *
 *
 * @author liuyanfei
 * @description
 * @date 2022/2/7 10:46 上午
 **/
public class StreamTest1 {


    /**
     * 一、创建 stream 的5种方式
     */
    @Test
    public void test1(){

        //1.
        Stream stream1 = Stream.of("1","2","3","4");


        //2.
        String[] strs = new String[]{"1","2","3","4"};
        Stream stream2 = Stream.of(strs);



        //3.
        List<String> list = Arrays.asList(strs);
        Stream stream4 = list.stream();


        //4.接受一个Supplier 函数式
        Stream<String> stream = Stream.generate(UUID.randomUUID()::toString);

        //5.生成一个无限的序列。以 seed 为初始值，UnaryOperator为生成规则
        Stream<Integer> stream5 = Stream.iterate(1, item -> item + 1).limit(6);

    }


    /**
     * 二、Stream 简单应用
     */
    @Test
    public void test2(){

        IntStream.of(new int[]{5, 6, 7}).forEach(System.out::println);
        System.out.println("-----");

        IntStream.range(3, 8).forEach(System.out::println);
        System.out.println("-----");

        IntStream.rangeClosed(3, 8).forEach(System.out::println);
    }



    /**
     * 三、Stream 的组成
     *    1.源
     *    2.零个或者多个中间部分
     *    3.终止部分
     *
     *
     *
     *   流操作的分类：
     *    1.惰性求值
     *    2.及早求值
     */
    @Test
    public void test3(){

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
        //1.list 是源
        //2.map 是中间组成部分，返回的也是 steam
        //3.reduce 终止部分 返回对应的类型


//        map 是惰性求值，只有到了reduce 的时候才会执行
//        reduce 是及早求值，运行到 reduce 就会返回结果


//reduce 函数，第一个参数是初始值，第二个参数接受一个 BinaryOperator 类型的函数式，apply 方法需要传入两个参数
//本例中reduce 第二个参数 使用了Integer::sum  类名::非静态方法名，所以是需要两个参数，满足  BinaryOperator 函数式的要求
        Integer reduce = list.stream()
                            .map(i -> 2 * i)
//                            .reduce(0, (i,j) -> i+j);
                            .reduce(0, Integer::sum);
        System.out.println(reduce);



    }


    /**
     * 三.Stream 转其他类型
     */

    /*
     * 1.  stream.toArray
     * 只能转成 数组
     */
    @Test
    public void test4(){
        Stream<String> stream = Stream.of("h", "w", "hw");

        //stream.toArray  生成器函数接受一个整数，它是所需数组的大小，并生成一个所需大小的数组。
        //toArray 接受一个 IntFunction的函数式
//        String[] strings = stream.toArray(length -> new String[length]);

        //IntFunction的函数式 传一个 int，返回一个值。(length -> new String[length]) 可简化为 String[]::new。这种方式往中括号传值也可以
        String[] strings = stream.toArray(String[]::new);
        List<String> list = Arrays.asList(strings);

        System.out.println(list);
    }

    /*
     * 2.stream.collect(Collectors.toList())
     * 可以把 steam 中的元素转成集合
     *
     */
    @Test
    public void test5(){
        Stream<String> stream = Stream.of("hello", "world", "helloworld");
        List<String> list = stream.collect(Collectors.toList());
        list.forEach(System.out::println);
    }


    /*
     *
     * 3.              stream.collect(
     *                                  R collect(Supplier<R> supplier,
     *                                  BiConsumer<R, ? super T> accumulator,
     *                                  BiConsumer<R, R> combiner
     *                              );
     * 可以转成各类型的数据，且可以自定义累加元素的规则，更灵活
     */
    @Test
    public void test6(){
        Stream<String> stream = Stream.of("hello", "world", "helloworld");

        //stream所有值都在 sourceSpliterator 中存着呢，当使用collect 转换成其他 List 类型后，sourceSpliterator就会变成 null
        //所以同一个 stream 当执行一次collect，第二次collect 时就会报错


       /*     <R> R collect(Supplier<R> supplier,
                BiConsumer<R, ? super T> accumulator,
                BiConsumer<R, R> combiner);


              supplier ：相当于一个初始值，来确定类型。
              accumulator ： 对两个值进行合并
              combiner： 实验中并未执行，即使故意写个错误也不执行，目前不知道是干啥用的

        */
        List<String> list = stream.collect(
                () -> new ArrayList(Arrays.asList("lyf")),
                (theList, item) -> {
                    System.out.println("第二 list:"+theList);
                    System.out.println("第二 item:"+item);
                    System.out.println("------------");
                    theList.add(item);
                    },
                (theList1, theList2) -> {
                    System.out.println("++++++++++++++");

                    String s = null;
                    s.length();
                    theList1.addAll(theList2);
                });
//        改成方法引用
//        List<String> list2 = stream.collect(LinkedList::new, LinkedList::add, LinkedList::addAll);

        list.forEach(System.out::println);

    }


    /*
     *   4.stream.collect(Collectors.toCollection(T::new))
     *
     *   可以转成 任何类型的集合
     */

    @Test
    public void test7(){
        Stream<String> stream = Stream.of("hello", "world", "helloworld");
        List<String> collect = stream.collect(Collectors.toCollection(ArrayList::new));



        //输出只有  helloworld hello
        // 说明底层是调用 set.add  一个一个元素 add 进去的
        Stream<String> stream2 = Stream.of("hello", "hello", "helloworld");
        HashSet<String> set = stream2.collect(Collectors.toCollection(HashSet::new));
        set.forEach(System.out::println);
    }


    /* 5.stream.collect(Collectors.joining());
     * 合并字符串
     */
    @Test
    public void test8(){
        Stream<String> stream = Stream.of("hello", "world", "helloworld");
       String collect = stream.collect(Collectors.joining());

        System.out.println(collect);

    }
}
