package iron.man.lyf.test;

import lombok.Data;
import org.junit.Test;

import java.util.*;
import java.util.function.Supplier;

/**
 *
 * Optional对象中有个 value 值。这个 value 值就是传统对象模式里面的对象。
 * 只不过现在把对象都交给Optional处理。防止空指针
 *
 * @author liuyanfei
 * @description
 * @date 2022/2/6 11:55 上午
 **/
public class OptionalTest {


    @Test
    public void test1(){
        /**
         *  一、创建 optional
         */
        //创建一个 value 值为空的 optional 对象
        Optional<String> optional = Optional.empty();

        //创建一个 value 值为 "123"的optional 对象
        Optional optional2 = Optional.of("123");

        //可选操作。
        // 如果传入的参数是个 null，那么就创建一个 value 为 null 的 optional。
        // 如果传入参数非 null，那么 value 值就是传入的这个对象
        Optional optional3 = Optional.ofNullable("123");


        /**
         * 二、简单使用Optional
         */
        //传统对象模式。这种的比较繁琐
        if(optional.isPresent()){
            System.out.println(optional.get());
        }


        //1. ifPresent
        //判断 value 是否为空。
        //          为空则不执行 System.out.println(optional.get())
        //          不为空执行 System.out.println(optional.get())
        optional.ifPresent(item -> System.out.println(optional.get()));



        //2. orElse
        //接受一个对象。如果optional的 value 为null，那么就返回 orElse 中传入的对象
        String world = optional.orElse("world");
        System.out.println(world);

        System.out.println("---------");


        //3. orElseGet
//        orElseGet接受一个 Supplier 函数。如果optional value 为 null。那么就返回 supplier.get()
        Supplier supplier = () -> "nihao";
        String s = optional.orElseGet(supplier);
        System.out.println(s);
    }


    /**
     * 三、实际应用
     *
     * 公司对 部分是一对多的关系。
     * 获取公司的所有部门，如果没有部门 则返回一个空集合
     *
     *
     */
    @Test
    public void test2(){

        //组织数据
        Employee employee = new Employee();
        employee.setName("zhangsan");

        Employee employee2 = new Employee();
        employee2.setName("lisi");

        Company company = new Company();
        company.setName("company1");

        List<Employee> employees = Arrays.asList(employee, employee2);
        company.setEmployees(employees);



        //1.传统写法

        List<Employee> employees2 = company.getEmployees();
        if(employees2 == null){
            employees2 = new ArrayList<>();
        }
        System.out.println(employees2);



        //2. 使用 Optional 的 lambda 写法
        Optional<Company> optional =  Optional.ofNullable(company);

        List<Employee> employees1 = optional
                                            .map(argCompany -> argCompany.getEmployees())
                                            .orElse(Collections.emptyList());
        System.out.println(employees1);

    }



}

@Data
class Employee {

    private String name;

}

@Data
class Company {

    private String name;

    private List<Employee> employees;

}
