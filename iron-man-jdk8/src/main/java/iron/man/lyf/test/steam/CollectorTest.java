package iron.man.lyf.test.steam;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * collect 中的 Collector参数
 * @author liuyanfei
 * @description
 * @date 2022/2/10 8:42 下午
 **/
public class CollectorTest {


        Student2 student1 = new Student2("zhangsan", 70);
        Student2 student6 = new Student2("zhangsan", 80);
        Student2 student2 = new Student2("lisi", 90);
        Student2 student3 = new Student2("wangwu", 100);
        Student2 student4 = new Student2("zhaoliu", 90);
        Student2 student5 = new Student2("zhaoliu", 90);
        List<Student2> list = Arrays.asList(student1, student2, student3, student4, student5,student6);

    @Test
    public void test1(){
        System.out.println("---------------");

        //1.输出list 的长度
        // 优先使用具体表达
        System.out.println(list.stream().collect(Collectors.counting()));
        //优先使用这个
        System.out.println(list.stream().count());
        System.out.println("---------------");

        //2.输出分数最 低/高 分的学生。minBy 最低，maxBy 最高
        //Collectors.minBy 需要一个 Comparator对象。Comparator.comparing返回一个 Comparator对象
        //不足：如果有多个最低，只会返回一个
        list.stream().collect(Collectors.minBy(Comparator.comparingInt(student -> student.getScore()))).ifPresent(System.out::println);
        System.out.println("---------------");

        //3.输出平均分
        System.out.println(list.stream().collect(Collectors.averagingInt(Student2::getScore)));
        //4.求和
        System.out.println(list.stream().collect(Collectors.summingInt(student -> student.getScore())));

        //5.信息汇总输出：IntSummaryStatistics{count=5, sum=450, min=80, average=90.000000, max=100}
        IntSummaryStatistics intSummaryStatistics = list.stream().collect(Collectors.summarizingInt(Student2::getScore));
        System.out.println(intSummaryStatistics);
        System.out.println("---------------");
        //6.元素拼接
        System.out.println(list.stream().map(Student2::getName).collect(Collectors.joining()));
        System.out.println(list.stream().map(Student2::getName).collect(Collectors.joining(", ")));
        System.out.println(list.stream().map(Student2::getName).collect(Collectors.joining(", ", "<begin> ", " <end>")));
    }


    /**
     * 嵌套分组
     */
    @Test
    public void test2(){
        //1.对分数进行分组后，再对名字进行分组
        Map<Integer, Map<String, List<Student2>>> collect =
                list.stream().collect(Collectors.groupingBy(Student2::getScore, Collectors.groupingBy(Student2::getName)));
        System.out.println(collect);
        System.out.println("------------");


        //2.80分以下的分一组，80-90一组，90以上一组
        Map<Boolean, Map<Boolean, List<Student2>>> map2 = list.stream().
                collect(Collectors.partitioningBy(student -> student.getScore() > 80, Collectors.partitioningBy(student -> student.getScore() > 90)));
        System.out.println(map2);
        System.out.println("------------");


        //3.80分以上 的数量
        Map<Boolean, Long> map4 = list.stream().
                collect(Collectors.partitioningBy(student -> student.getScore() > 80, Collectors.counting()));
        System.out.println(map4);

    }

    /**
     * collectingAndThen
     */
    @Test
    public void test3(){
        //        通过 name 分组，返回每组里面分数最小的那一组数据
//        collectingAndThen(Collector<T,A,R> downstream,Function<R,RR> finisher)
//        downstream为Collector 表达式，finisher 是对Collector 表达式执行后的结果再操作
//        下面逻辑Collectors.minBy() 执行后返回一个 Optional，接下来 通过 get 把Optional 里的数据取出来

        Map<String, Student2> map5 = list.stream().
                collect(Collectors.groupingBy(
                        Student2::getName,
                        Collectors.collectingAndThen(Collectors.minBy(Comparator.comparingInt(Student2::getScore)), Optional::get)));
        System.out.println(map5);


    }
}


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
class Student2 {
    private String name;

    private int score;
}