package iron.man.lyf.test.steam.collector;


import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import static java.util.stream.Collector.Characteristics.IDENTITY_FINISH;
import static java.util.stream.Collector.Characteristics.UNORDERED;

//Collector的泛型：     public interface Collector<T, A, R>
public class MySetCollector<T> implements Collector<T, Set<T>, Set<T>>{


    /**
     * A function that creates and returns a new mutable result container.
     * 创建并返回一个新的可变结果容器的函数
     *
     * 返回一个新的可变结果容器的函数
     * @return a function which returns a new, mutable result container
     */

    @Override
    public Supplier<Set<T>> supplier() {
        System.out.println("supplier invoked!");
        return HashSet<T>::new;
    }


    /**
     * A function that folds a value into a mutable result container.
     * 将值折叠到可变结果容器中的函数。
     *
     * @return a function which folds a value into a mutable result container
     * 将一个值折叠到一个可变的结果容器中的函数
     */
    @Override
    public BiConsumer<Set<T>, T> accumulator() {
        System.out.println("accumulator invoked!");
        return Set<T>::add;
    }



    /**
     * A function that accepts two partial results and merges them.  The
     * combiner function may fold state from one argument into the other and
     * return that, or may return a new result container.
     *
     * 这个函数接受两个参数并将其合并。combiner函数可以将状态从一个参数折叠到另一个参数并返回，也可以返回一个新的结果容器。
     *
     * @return a function which combines two partial results into a combined
     * result
     * 将两个参数值 组合成一个组合结果
     */
    @Override
    public BinaryOperator<Set<T>> combiner() {
        System.out.println("combiner invoked!");
        return (set1, set2) -> {
            set1.addAll(set2);
            return set1;
        };
    }



    /**
     * Perform the final transformation from the intermediate accumulation type
     * {@code A} to the final result type {@code R}.
     *
     * <p>If the characteristic {@code IDENTITY_FINISH} is
     * set, this function may be presumed to be an identity transform with an
     * unchecked cast from {@code A} to {@code R}.
     *
     * @return a function which transforms the intermediate result to the final
     * result
     *
     * 从中间累加类型A到最终结果类型R进行最终转化。
     * 如果设置了特征的IDENTITY_FINISH，则可以假定该函数是一个标识转换，并进行了未检查的从A到R的转换
     * 其实 设置了IDENTITY_FINISH，这个函数不会被执行
     *
     */
    @Override
    public Function<Set<T>, Set<T>> finisher() {
//        System.out.println("finisher invoked!");
//        return Function.identity();
        throw new UnsupportedOperationException();
    }



    /**
     * Returns a {@code Set} of {@code Collector.Characteristics} indicating
     * the characteristics of this Collector.  This set should be immutable.
     *
     * @return an immutable set of collector characteristics
     *
     *
     * 返回收集器的集合。表示此采集器的特性。这个集合是不可变的
     *
     */
    @Override
    public Set<Characteristics> characteristics() {
        System.out.println("characteristics invoked!");
        return Collections.unmodifiableSet(EnumSet.of(IDENTITY_FINISH, UNORDERED));
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello", "world", "welcome", "hello");
        Set<String> set = list.stream().collect(new MySetCollector<>());

        System.out.println(set);
    }
}
























