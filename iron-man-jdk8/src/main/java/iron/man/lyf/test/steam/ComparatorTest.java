package iron.man.lyf.test.steam;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author liuyanfei
 * @description
 * @date 2022/2/10 10:23 下午
 **/
public class ComparatorTest {


    /**
     * 排序(排序默认按照字母表来排序)
     */
    @Test
    public void test1(){

        //1.使用函数式定义比较规则：递增
        List<String> list = Arrays.asList("anihao", "chello", "bworld", "dwelcome");
        Collections.sort(list, (item1, item2) -> item1.length() - item2.length());
        System.out.println(list);


        //2.使用函数式定义比较规则：递减
        Collections.sort(list, (item1, item2) -> item2.length() - item1.length());
        System.out.println(list);


        //3.使用 Comparator.comparingInt 比较规则：递增
        Collections.sort(list, Comparator.comparingInt(String::length));
        System.out.println(list);


        //4.使用 Comparator.comparingInt 比较规则：递减
        Collections.sort(list, Comparator.comparingInt(String::length).reversed());
        System.out.println(list);

//      因为reversed()  的存在  如果 lambda 表达式 不确定类型会报错。如下
//        Collections.sort(list, Comparator.comparingInt((/*String */item) -> item.length()).reversed());




        //5.list.sort 法
        list.sort(Comparator.comparingInt(String::length).reversed());
        list.sort(Comparator.comparingInt((String item) -> item.length()).reversed());




        //6. 二次排序：.thenComparing() 接第一次排序  返回为0的继续 排序
            //String.CASE_INSENSITIVE_ORDER：忽略大小写，按字母表排序
        List<String> list2 = Arrays.asList("banihao", "bchello", "bBworld", "dwelcome");
        Collections.sort(list2, Comparator.comparingInt(String::length)
                                    .thenComparing(String.CASE_INSENSITIVE_ORDER)
        );
        System.out.println(list2);

        //与上面 等价

        Collections.sort(list2, Comparator.comparingInt(String::length).
                thenComparing((item1, item2) -> item1.toLowerCase().compareTo(item2.toLowerCase())));
        System.out.println(list2);

//        与上面等价
        Collections.sort(list2, Comparator.comparingInt(String::length).
                thenComparing(Comparator.comparing(String::toLowerCase)));
        System.out.println(list2);


        /**
         * 二次排序，需要理解一个排序分组的概念。
         * 如 7.1分析：
         *  1.第一次根据长度排序 会把list2 字符串分为   第一组：["banihao", "bchello", "bBworld"]  第二组 ["dwelcome"]
         *    当然按照默认递增的排序  第二组 是排在最后的。所以第一次排序就是 "banihao", "bchello", "bBworld", "dwelcome"
         *  2.第二次进行字母表倒序排序 会在第一组的基础上再次进行分组：第一组：[["banihao"], ["bchello"], ["bBworld"]]  第二组 ["dwelcome"]
         *    第二次排序后的结果就是：bchello, bBworld, banihao, dwelcome
         *  3.到此只有四个元素的集合被细分成了 4组，每组元素都分成了最小单元。所以之后再进行排序，这个顺序是都不会再变了。
         *
         *
         *
         * 7.2分析：
         *  1.第一次根据 length 排序 list3会分成两组：["A123","a123","A123", "b123", "c123"][ "a1234", "c1234", "b1234"]
         *      所以第一次排序list3会变成：[A123, a123, A123, b123, c123, a1234, c1234, b1234]
         *
         *  2.第二次根据字母表反序(忽略大小写) 会再把数据分成以下分组：[[c123], [b123], [A123, A123, a123]] [c1234, b1234, a1234]
         *      所以第二次排序 list3会变成 [c123, b123, A123, A123, a123, c1234, b1234, a1234]
         *
         *  3.第三次 根据字母表顺序排序，并且不忽略大小写，会吧数据分成以下分组：[[c123], [b123], [a123],[A123, A123]]] [c1234, b1234, a1234]
         *      所以最后的list3会变成：[c123, b123, a123, A123, A123, c1234, b1234, a1234]
         */
        //7.1  二次排序  倒序
        Collections.sort(list2,Comparator.comparing(String::length)
                .thenComparing(Comparator.comparing(String::toLowerCase,Comparator.reverseOrder())));
        System.out.println(list2);

        //与上面排序是一样的

        Collections.sort(list2, Comparator.comparingInt(String::length).
                thenComparing(String::toLowerCase, Comparator.reverseOrder()).
                thenComparing(String::toLowerCase, Comparator.reverseOrder()));

        System.out.println(list2);




        //7.2  二次排序
        List<String> list3 = Arrays.asList("A123","A123", "b123","a123", "c123", "a1234", "c1234", "b1234");

        Collections.sort(list3, Comparator.comparingInt(String::length)
                .thenComparing(String::toLowerCase, Comparator.reverseOrder())
        );

        System.out.println(list3);


        Collections.sort(list3, Comparator.comparingInt(String::length)
                        .thenComparing(String::toLowerCase, Comparator.reverseOrder())
                        .thenComparing(Comparator.reverseOrder())
        );

        System.out.println(list3);




    }
}
