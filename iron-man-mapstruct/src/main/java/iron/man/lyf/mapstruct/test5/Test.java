package iron.man.lyf.mapstruct.test5;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author liuyanfei
 * @description
 * @date 2021/12/9 10:06 上午
 **/
public class Test {

    public void fun1() {
        List<User> list = new ArrayList<>();
            list.add(User.builder().id(123L).nickname("lyf1").createTime(new Date()).build());
            list.add(User.builder().id(123L).nickname("lyf2").createTime(new Date()).build());
            list.add(User.builder().id(123L).nickname("lyf3").createTime(new Date()).build());

        PageBean<User> userPageBean = new PageBean<>();
        userPageBean.setPages(10);
        userPageBean.setPageNum(5);
        userPageBean.setPageSize(8);
        userPageBean.setResult(list);
        userPageBean.setTotal(86L);
        userPageBean.setUrl("getUserByCondition/");

        PageBean<UserBO> userBOPageBean= UserMapper.INSTANCE.user2UserBO(userPageBean);
        System.out.println(userBOPageBean);
    }

    public void fun2() {


        List<User> list = new ArrayList<>();
            list.add(User.builder().id(123L).nickname("lyf1").createTime(new Date()).build());
            list.add(User.builder().id(123L).nickname("lyf2").createTime(new Date()).build());
            list.add(User.builder().id(123L).nickname("lyf3").createTime(new Date()).build());

        PageBean<User> userPageBean = new PageBean<>();
        userPageBean.setPages(10);
        userPageBean.setPageNum(5);
        userPageBean.setPageSize(8);
        userPageBean.setResult(list);
        userPageBean.setTotal(86L);
        userPageBean.setUrl("getUserByCondition/");

        PageBean<UserBO> userBOPageBean = new PageBean<>();
        UserMapper.INSTANCE.user2UserBO(userPageBean,userBOPageBean);
        System.out.println(userBOPageBean);
    }


    public static void main(String[] args) {
        Test test = new Test();
        test.fun1();
//        test.fun2();
    }
}

