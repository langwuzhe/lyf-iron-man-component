package iron.man.lyf.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * 方法引用有4种方法：
 *      1.类名::静态方法名
 *      2.引用名::实例方法名
 *      3.类名:: 方法名
 *      4.类名::new
 *
 *
 * @author liuyanfei
 * @description
 * @date 2022/2/6 7:53 下午
 **/
public class MethodReferenceTest {

    @Test
    public void test1(){
        Student student1 = new Student("zhangsan", 10);
        Student student2 = new Student("lisi", 90);
        Student student3 = new Student("wangwu", 50);
        Student student4 = new Student("zhaoliu", 40);

        List<Student> studentList = Arrays.asList(student1, student2, student3, student4);


        /**
         * 0.进化
         */


//        1）内部类格式
        /*studentList.sort(new Comparator<Student>() {
                      @Override
                      public int compare(Student s1, Student s2) {
                          return s1.getScore() - s2.getScore();
                      }
                  });*/

//        2）lambda 直接函数
        //List.sort 需要传一个 Comparator函数式，即 传两个参数，返回一个 int 类型的值即可。
        //即使这样写都可以：studentList.sort((s1,s2) -> 1);  只不过逻辑会不对
//        studentList.sort((s1,s2) -> s1.getScore() - s2.getScore());

        //3）lambda 调用静态方法
        // 因为函数体返回一个 int 类型的数据即可，所以可以把函数放到一个静态方法里面，这样写
//        studentList.sort((s1,s2) -> StudentComparator.staticCompareStudentByScore(s1,s2));


        /**
         * 1.类名::静态方法名
         *
         * 调用方法必须传入两个参数
         */
        studentList.sort(StudentComparator::staticCompareStudentByScore);


        /**
         * 2.引用名::实例方法名
         * 调用方法必须传入两个参数
         */
        StudentComparator studentComparator = new StudentComparator();
        studentList.sort(studentComparator::compareStudentByScore);





        /**
         * 调用方法必须传入一个参数。且通过 this 可以拿到当前的对象
         * 3.类名:: 方法名
         *
         * Comparator.compare  本来是需要两个参数的。
         * 但是Student 的 compareByScore 方法只需要一个参数
         *
         * 所以 类名:: 方法名 方法名是一个特例(只需要一个参数)，这种书写方式翻译过来应该是这样
         *
         * 集合中  拿出两个元素分别是 s1，s2。然后组成表达式 ：s1.compareByScore(s2)
         *
         *
         * 反过来考虑：使用  类名:: 非静态方法名，这个非静态方法必须只能传一个参数，且这个函数式接口的抽象方法必须得传两个参数
         *
         */



        studentList.sort(Student::compareByScore);

        studentList.forEach(s -> System.out.println(s.getScore()));


        //groupingBy 的  Function.apply(T t) 需要一个参数，那么Student.getName就不需要参数了。相当于只传一个 this
        Collectors.groupingBy(Student::getName);


        /**
         * 构造器方法引用
         *
         * 4.类名::new
         *
         * 接收者必须也是一个函数式接口。
         *
         *
         */

        TestForeMethod testForeMethod = new TestForeMethod();
        testForeMethod.getString(String::new );
        testForeMethod.getString2("你好",String::new );
    }

}


@Data
@AllArgsConstructor
@NoArgsConstructor
class Student {

    private String name;

    private int score;


    public String getName() {
        return name;
    }

    public int compareByScore(Student student) {

        return this.getScore() - student.getScore();
    }


}



class StudentComparator{
    public static int staticCompareStudentByScore(Student student1, Student student2) {
        return student1.getScore() - student2.getScore();
    }


    public int compareStudentByScore(Student student1, Student student2) {
        return student1.getScore() - student2.getScore();
    }

}

class TestForeMethod{
    public String getString(Supplier<String> supplier) {
        return supplier.get() + "test";
    }

    public String getString2(String str, Function<String, String> function) {
        return function.apply(str);
    }

}