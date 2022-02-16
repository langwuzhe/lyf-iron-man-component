package iron.man.lyf.test.base_function;

import org.junit.Test;

import java.util.function.Consumer;

/**
 *
 * Consumer 接收一个参数  不返回值
 *
 *
 * @author liuyanfei
 * @description
 * @date 2022/2/6 10:24 上午
 **/
public class ConsumerTest {


    /**
     * 一、概述
     * Consumer 是只能接收一个参数，但是没有返回值的。
     *
     *
     */
    @Test
    public void test1(){
//        所以如果传进来一个值后，不能有任何返回
        this.computer(5,integer -> System.out.println(integer * integer));
        //下面这个表达式会报错 这样写相当于  return integer * integer;
//        this.computer(5,integer -> integer * integer);
    }


    public void computer(Integer x,Consumer<Integer> integerConsumer){
        integerConsumer.accept(x);
    }
}
