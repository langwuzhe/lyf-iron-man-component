package iron.man.lyf.test.steam;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *
 * stream 更多应用2
 *
 * @author liuyanfei
 * @description
 * @date 2022/2/9 2:00 下午
 **/
public class StreamTest3 {

    /**
     * 把这堆单词 去重
     */
    @Test
    public void test1(){
        List<String> list = Arrays.asList("hello welcome", "world hello", "hello world hello", "hello welcome");

        list.stream()
                //只能转成 {String[],String[],String[]} 这种形式。
                .map(item -> item.split(" "))
                .distinct()
                .collect(Collectors.toList())
                .forEach(System.out::println);
        System.out.println("--------------");
        list.stream()
                .map(item -> item.split(" "))
                //把每个数组打成 stream，然后利用 flat 打平。返回一个大 stream
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList())
                .forEach(System.out::println);

    }


    /**
     * 两个 list 笛卡尔积
     */
    @Test
    public void test2(){
        List<String> list1 = Arrays.asList("Hi", "Hello", "你好");
        List<String> list2 = Arrays.asList("zhangsan", "lisi", "wangwu", "zhaoliu");

        List<String> result = list1.stream()
                //这里如果用 map 返回的是List<Stream<String>>
                .flatMap(item -> list2.stream().map(item2 -> item + " " + item2))
                .collect(Collectors.toList());
        result.forEach(System.out::println);

    }

    /**
     * groupingBy、partitioningBy
     *
     *
     * 分组。请联想sql 分组函数
     */
    @Test
    public void test3(){
        Student student1 = new Student("zhangsan", 100, 20);
        Student student2 = new Student("lisi", 90, 20);
        Student student3 = new Student("wangwu", 90, 30);
        Student student4 = new Student("zhangsan", 80, 40);
        List<Student> students = Arrays.asList(student1, student2, student3, student4);



//        根据 name 分组
        Map<String, List<Student>> collect1 = students.stream().collect(Collectors.groupingBy(Student::getName));
//        根据 score 分组
        Map<Integer, List<Student>> collect2 = students.stream().collect(Collectors.groupingBy(Student::getScore));
        //分组后统计集合长度
        Map<String, Long> collect3 = students.stream().collect(Collectors.groupingBy(Student::getName, Collectors.counting()));
//        分组后统计 score 的平均数
        Map<String, Double> collect4 = students.stream().collect(Collectors.groupingBy(Student::getName, Collectors.averagingDouble(Student::getScore)));

//        partitioningBy是groupingBy 的特例。返回数据的 key 必须是 boolean 类型的
        Map<Boolean, List<Student>> collect5 = students.stream().collect(Collectors.partitioningBy(student -> student.getScore() >= 90));

        System.out.println(collect5);
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
class Student {

    public String getName() {
        return name;
    }

    private String name;

    private int score;

    private int age;


}