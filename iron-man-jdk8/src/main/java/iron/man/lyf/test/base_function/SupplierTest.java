package iron.man.lyf.test.base_function;

import lombok.Data;
import org.junit.Test;

import java.util.function.Supplier;

/**
 * @author liuyanfei
 * @description
 * @date 2022/2/6 10:40 上午
 *
 *
 * Supplier 不接收参数 返回一个值
 *
 **/
public class SupplierTest {


    /**
     * 一、简单使用
     */

    @Test
    public void test1(){
        Supplier<String> supplier = () -> "hello world";
        System.out.println(supplier.get());


    }


    /**
     * 二、常用做工厂
     */
    @Test
    public void test2(){
        Supplier<Student> supplier = () -> new Student();
        System.out.println(supplier.get().getName());

        System.out.println("-------");

        Supplier<Student> supplier2 = Student::new;
        System.out.println(supplier2.get().getName());
    }

}

@Data
class Student {

    private String name = "zhangsan";

    private int age = 20;


    }