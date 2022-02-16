package iron.man.lyf.md;

import iron.man.lyf.test.MethodReferenceTest;
import iron.man.lyf.test.OptionalTest;
import iron.man.lyf.test.base_function.ConsumerTest;
import iron.man.lyf.test.base_function.PredicateTest;
import iron.man.lyf.test.base_function.SupplierTest;
import iron.man.lyf.test.base_function.funciton.FunctionTest;
import iron.man.lyf.test.base_function.lambda.Lambda;
import iron.man.lyf.test.defaultmethod.MyClass;
import iron.man.lyf.test.steam.*;

/**
 * @author liuyanfei
 * @description
 * @date 2022/2/16 5:47 下午
 **/
public class readerme {
    public void test(){

        //一、lambda 小试牛刀
        Class<Lambda> lambdaClass = Lambda.class;

        //二、jdk 提供的经典 函数式接口。
            //1.consumer:  void accept(T t); 接收一个参数，无返回值
            Class<ConsumerTest> consumerTestClass = ConsumerTest.class;

            //2.function：  R apply(T t);   接收一个参数，有返回
            Class<FunctionTest> functionTestClass = FunctionTest.class;

            //3.predicate：boolean test(T t); 接收一个参数，返回布尔类型。function的特例
            Class<PredicateTest> predicateTestClass = PredicateTest.class;

            //4. Supplier：T get();   无参数，有返回
            Class<SupplierTest> supplierTestClass = SupplierTest.class;


        //三、关于接口中  default 方法的实验
        Class<MyClass> myClassClass = MyClass.class;


        //四、方法引用,双冒号 ::
        Class<MethodReferenceTest> methodReferenceTestClass = MethodReferenceTest.class;


        //五、Optional：防空指针
        Class<OptionalTest> optionalTestClass = OptionalTest.class;


        //六、Stream
        Class<StreamTest1> steamTest1Class = StreamTest1.class;
        Class<StreamTest2> steamTest2Class = StreamTest2.class;
        Class<StreamTest3> steamTest3Class = StreamTest3.class;

        //七、collector  收集器的应用
        Class<CollectorTest> collectorTestClass = CollectorTest.class;

        //八、Comparator   比较器
        Class<ComparatorTest> comparatorTestClass = ComparatorTest.class;



    }
}
