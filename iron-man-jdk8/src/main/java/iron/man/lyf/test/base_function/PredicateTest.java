package iron.man.lyf.test.base_function;

import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author liuyanfei
 * @description
 * @date 2022/2/5 11:29 下午
 *
 * Predicate
 *
 *
 *
 **/
public class PredicateTest {


    /**
     * 一、小试牛刀
     * Predicate 其实是相当于 Function 写死了返回值为 boolean。只不过Predicate 还定义了更丰富的与或非的方法。
     * 如下程序，上半部分演示 Predicate，下半部分演示 Function 把返回值定义为 boolean的操作。
     *
     *
     *Predicate 接收一个参数，返回一个 boolean 值
     */

    @Test
    public void predicateTest1(){
        //一定要牢记  函数式接口，有且只有一个抽象方法
        //函数式接口Predicate 的抽象方法是 test，该方法的返回值是 boolean 类型的。
        Predicate<String> predicateTest = (str) -> {
           return str.length() == 5;
        };
        boolean length = predicateTest.test("length");
        System.out.println(length);



        System.out.println("-------------------");
        //其实是相当于 函数式接口Function，写死了返回值类型，上面的程序等价于  下面程序
        Function<String,Boolean> functionTest = str -> str.length() == 5;
        Boolean length1 = functionTest.apply("length");
        System.out.println(length1);

    }


    /**
     * 二、Predicate 的与或非
     */
    @Test
    public void predicateTest2(){
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        //调用andPredicateExcute 测试 Predicate.and
        andPredicateExcute(list,integer -> integer % 2==0,integer -> integer >5);



        //上面通过andPredicateExcute方法传值，在andPredicateExcute中执行其实等价于下面操作
        //只不过 predicate1、predicate2 的行为就被写死了，失去了灵活变动的空间。此处只为测试。
        //通过输出可以看到。只有predicate1执行为 true 的时候才会执行predicate2.所以 Predicate.and 是个短路操作
        Predicate<Integer> predicate1 = integer -> integer % 2==0;

        Predicate<Integer> predicate2 = integer -> {
            System.out.println("执行了predicate2,当前值："+integer);
            return integer >5;
        };


        list.forEach(integer -> {
            if(predicate1.and(predicate2).test(integer)){
                System.out.println(integer);
            }
        });



        //or not  negate 同样的原理，不再演示

    }

    void andPredicateExcute(List<Integer> list,Predicate<Integer> predicate1,Predicate<Integer> predicate2){
        list.forEach(integer -> {
            //predicate1 执行 test 的值   && predicate2 test的值。
            //      如果其中有一个test 返回 false 则(predicate1.and(predicate2)).test(?) 返回 false
            //      两个 test全是 true ，则(predicate1.and(predicate2)).test(？) 返回 true
            if((predicate1.and(predicate2)).test(integer)){
                System.out.println(integer);
            }
        });
    }


    /**
     * 三、isEqual 源码
     *     static <T> Predicate<T> isEqual(Object targetRef) {
     *         return (null == targetRef)
     *                 ? Objects::isNull
     *                 : object -> targetRef.equals(object);
     *     }
     *
     *
     * 因为 Objects.isNull 方法也是返回的 boolean 值，所以 符合Predicate对象的定义，所以Objects::isNull 作为返回类型没问题
     *
     *  equals方法返回的也是 boolean 同样符合Predicate对象的定义
     *
     *
     *
     *
     */

    @Test
    public void testIsEqual(){
        //返回 str1.equal(str2) 结果

        String str1= "str1";
        String str2= "str2";


        boolean test = Predicate.isEqual(str1).test(str2);

        System.out.println(test);
    }


}
