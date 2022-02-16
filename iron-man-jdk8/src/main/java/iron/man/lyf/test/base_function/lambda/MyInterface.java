package iron.man.lyf.test.base_function.lambda;

/**
 * @author liuyanfei
 * @description
 * @date 2022/2/4 11:56 上午
 **/

/**
 *
 *
 *
 * 关于函数式接口：
 * 1.如果一个接口只有一个抽象方法，那么该接口就是一个函数式接口。（如果接口是重写的 object 对象的接口，这个方法不算到抽象方法里面）
 * 2.如果我们在某个接口上声明了FunctionalInterface 注解，那么编译器就会按照函数式接口的定义来要求该接口
 * 3.如果某个接口只有一个抽象方法，但我们并没有给该接口声明FunctionalInterface 注解，那么编译器依旧会将该接口看作是函数式接口
 *
 *
 * Note that instances of functional interfaces can be created with lambda expressions, method references, or constructor references
 *注意，函数接口的实例可以通过lambda表达式、方法引用或构造函数引用创建
 */
@FunctionalInterface
public interface MyInterface{
    void test();
    /*void test2();*/



//    虽然这个接口里面有两个抽象方法，但是 toString 是 Object 的方法，所以还是符合函数式接口的定义。只有 test()一个抽象方法
     @Override
     String toString();

}
