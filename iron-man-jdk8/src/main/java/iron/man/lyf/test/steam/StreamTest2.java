package iron.man.lyf.test.steam;

import org.junit.Test;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 *
 * stream 更多应用1
 *
 *
 * @author liuyanfei
 * @description
 * @date 2022/2/7 10:30 下午
 **/
public class StreamTest2 {


    /**
     * map
     */
    @Test
    public void test1(){
        List<String> list = Arrays.asList("hello", "world", "helloworld", "test");
        list.stream().map(String::toUpperCase).collect(Collectors.toList()).forEach(System.out::println);

        System.out.println("----------");

        List<Integer> list2 = Arrays.asList(1, 2, 3, 4, 5);
        list2.stream().map(item -> item * item).collect(Collectors.toList()).forEach(System.out::println);

        System.out.println("----------");


    }


    /**
     * 2. flatMap
     * 可以把 集合中的集合  全部压成  一个集合
     *
     * flat  是扁平的意思
     */

    @Test
    public void test2(){
        Stream<List<Integer>> stream = Stream.of(Arrays.asList(1), Arrays.asList(2, 3), Arrays.asList(4, 5, 6));
        Stream<List<Integer>> stream2 = Stream.of(Arrays.asList(1), Arrays.asList(2, 3), Arrays.asList(4, 5, 6));


        //执行toArray 得到的是一个 三个元素的集合,每个数组里面都是一个集合
        List<List<Integer>> collect = stream2.collect(Collectors.toList());
        //输出：[[1], [2, 3], [4, 5, 6]]
        System.out.println(collect);

        //执行toArray  得到的是一个 6个元素的集合，把以前集合里面的东西都挤压到一个集合中了
        List<Integer> collect1 = stream.flatMap(theList -> theList.stream()).collect(Collectors.toList());
        //输出：[1, 2, 3, 4, 5, 6]
        System.out.println(collect1);

    }


    /**
     *
     * 3.stream 一些方法
     * findFirst、skip、max/min、IntSummaryStatistics
     * 获取 Stream 第一个元素
     */
    @Test
    public void test3(){
        //1.findFirst
        Stream<String> stream = Stream.generate(UUID.randomUUID()::toString);
        stream.findFirst().ifPresent(System.out::println);


        //2.skip 跳过两个元素
        Stream<Integer> stream2 = Stream.iterate(1, item -> item + 2).limit(6);

            //大于2的所有元素 乘以2，去除前2个元素后的 两个元素
        int sum = stream2
                        .filter(item -> item > 2)
                        .mapToInt(item -> item * 2)
                        //跳过两个元素
                        .skip(2)
                        .limit(2)
                        .sum();


        //3.max/min  找到流中最大、最小的值。且IntStream 必须是 Int 类型
         IntStream stream3 = IntStream.of(1,2,3,45,45);
         stream3.max().ifPresent(System.out::println);



         //4.IntSummaryStatistics（总结统计） 可以对一个流进行多次统计，只限于  IntStream
         IntStream stream4 = IntStream.of(1,2,3,45,45);
         IntSummaryStatistics summaryStatistics = stream4.summaryStatistics();

        System.out.println(summaryStatistics.getMin());
        System.out.println(summaryStatistics.getCount());
        System.out.println(summaryStatistics.getMax());
    }


    /**
     * stream stream 短路问题
     */
    @Test
    public void test4(){

        /*需求：找出来集合中 第一个字符长度是5的元素长度
         *
         * 因为有 findFirst函数，所以只取第一个符合的元素，取到了就不再往下循环了
         *
         * 第一个hello1 不符合，第二个world符合
         * 所以下面程序输出
         * hello1
         * world
         * 5
         *
         *
         */
        List<String> list = Arrays.asList("hello1", "world", "hello world");

        list.stream().mapToInt(item -> {
            int length = item.length();
            System.out.println(item);
            return length;
        }).filter(length -> length == 5).findFirst().ifPresent(System.out::println);

    }




}
