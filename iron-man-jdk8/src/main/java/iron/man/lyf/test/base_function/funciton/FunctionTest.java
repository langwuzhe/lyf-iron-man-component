package iron.man.lyf.test.base_function.funciton;



import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *Function 接收一个参数，返回一个值
 *BiFunction 接收两个参数，返回一个值
 */


public class FunctionTest {


    /**
     * 一、Function 函数
     */

    @Test
    public void testFunction(){
        Function<Integer, Integer> function =  value -> value*2;
        Integer apply = function.apply(3);
        System.out.println(apply);
    }


    /*1. compose
     *
     *
     * demo01 是 lambda方式
     * demo02 是 把内部类抽出来的方式
     *
     * 通过 demo02 可以看出 。
     *  Function 的 compose方法，是先 *组装* function2的apply 方法。得到返回值后，把返回值当作入参传到function1的apply 方法中。
     *  上文提到是组装而不是执行。 是因为compose 方法只是把function1、function2的逻辑柔和在一起。相当于把两个炮仗揉和在一起，然后在顺出一根炮捻子。
     *  然后 调用apply 点燃炮捻子。 分别炸响function2  然后 function1
     *
     */

    @Test
    public void demo01(){
        Function<Integer, Integer> function1 =  value -> value * 3;
        Function<Integer, Integer> function2 = value -> value * value;

        Integer apply = (function1.compose(function2)).apply(4);
        System.out.println("最后结果:"+apply);
    }




    @Test
    public void demo02() {
        //自定义了MyFunction，和 jdk 的Function接口一个意思。只不过把 lambda 改成了内部类的方式
        MyFunction<Integer, Integer> function1 = new MyFunction<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) {
                return integer * 3;
            }
        };

        MyFunction<Integer, Integer> function2 = new MyFunction<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) {
                return integer * integer;
            }
        };
        //使用 compose 组装完毕后，顺出炮捻子。调用apply 点燃炮捻子。 分别炸响function2  然后 function1
        Integer apply = (function1.compose(function2)).apply(4);


        System.out.println("最后结果:"+apply);
    }


    /*
     * 2.andThen
     *
     * andThen 和 compose 原理一样。都是先组织 function 然后再点炮捻子。
     * 只不过和 compost 不同的是，炮仗点着以后，先执行function1，拿到结果再传到 function2执行。所以 andThen 这个方法名就叫的很贴切了。顺序执行方法
     */
    @Test
    public void testAndThen() {
        Function<Integer, Integer> function1 =  value -> value * 3;
        Function<Integer, Integer> function2 = value -> value * value;

        Integer apply = (function1.andThen(function2)).apply(4);
        System.out.println("最后结果:"+apply);

    }


    /** 二、BiFunction
     *  可以传两个参数的 Function
     * @return
     */

    @Test
    public void testBiFunction() {
        BiFunction<Integer, Integer, Integer> biFunction = ((value1, value2) -> value1 + value2);
        Integer apply = biFunction.apply(1, 2);
        System.out.println(apply);
    }

    /*
     * BiFunction.andThen
     * 先执行biFunction 的函数，再执行 Function 的函数
     * 注意：andThen 的参数是 Function类型的。因为先执行 BiFunction，而BiFunction的返回值只有一个，所以再接着传下去就只有一个参数了。所以得使用Function
     */
    @Test
    public void testBiFunctionAndThen() {

        BiFunction<Integer, Integer, Integer> biFunction = (value1,value2) -> value1+value2;
        Function<Integer, Integer> function = value -> value *2;

        Integer apply = biFunction.andThen(function).apply(2, 3);

        System.out.println(apply);
    }

    /*
     *BiFunction 没有 compose。
     * 如果BiFunction 有compose方法只能是这样的
     * Integer apply = biFunction.compose(biFunction2).apply(2, 3);
     * 或
     * Integer apply = biFunction.compose(function).apply(2, 3);
     *
     * 先执行 biFunction2 或 function  都只有一个返回值，但是再执行biFunction需要两个参数。就出现了悖论。
     *
     */



//------------------------------------------------------------------------------------------------------------------------

    /**
     * BiFunction 的实际应用。
     * 第一部分  直接使用了 lambda 表达式
     *
     * 第二部分  使用了BiFunction 把需要目标字符串和原始集合传入
     *
     * 第三部分  通过一个方法(puppet),把两个参数和 行为都写到一块
     */


    @Test
    public  void demoBiFunction(){
        Person p1 = new Person("xiaoming",22);
        Person p2 = new Person("xiaoming",28);
        Person p3 = new Person("honghong",35);
        Person p4 = new Person("lvlv",16);
        List<Person> personList = Arrays.asList(p1,p2,p3,p4);
        String target = "xiaoming";

        //----------------第一部分
        //直接 lambda 表达式
        List<Person> collect = personList
                                        .stream()
                                        .filter(person -> person.getName().equals(target))
                                        .collect(Collectors.toList());
        collect.forEach(System.out::println );


        System.out.println("-------------------------------------------");

        //----------------第二部分
        //先使用BiFunction 构造一个函数（这个函数，其实就是用 lambda 表达式把符合的数据找出来）
        //BiFunction 的三个泛型：入参、入参、返回
        //                                                          argTarget,argPersonList 相当于两个形参
        BiFunction<String,List<Person>,List<Person>> biFunction = (argTarget,argPersonList) ->{
            return argPersonList
                        .stream()
                        .filter(person -> person.getName().equals(argTarget))
                        .collect(Collectors.toList());
        };
        //上面函数构造完了，但是不会执行，必须得调用 apply 才能执行
        // 把上面的两个参数传到  BiFunction 函数中
        List<Person> apply = biFunction.apply(target, personList);
        apply.forEach(System.out::println );


        System.out.println("-------------------------------------------");

        //第三部分 ---------------------------------
        // 通过一个方法(puppet),把两个参数和 行为都写到一块。然后在方法里面去开始执行
        List<Person> puppet = puppet(23, personList, (argTarget, argPersonList) -> {
            return argPersonList
                    .stream()
                    .filter(person -> person.getAge() < argTarget)
                    .collect(Collectors.toList());
        });
        puppet.forEach(System.out::println );
    }



    //壳子方法
    public List<Person> puppet(Integer age,List<Person> personList,BiFunction<Integer,List<Person>,List<Person>> biFunction){
        return biFunction.apply(age,personList);
    }

}


@Data
@AllArgsConstructor
class Person{
    private String name;
    private Integer age;
}