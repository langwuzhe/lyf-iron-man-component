package iron.man.lyf.mapstruct.test4;

import java.time.LocalDateTime;

/**
 * 数据类型不一样
 * @author liuyanfei
 * @description
 * @date 2021/12/8 10:58 下午
 **/
public class Test {
    public static void fun() {
        User user = User.builder().id(123L).nickname("lyf").createTime(LocalDateTime.now()).build();
        System.out.println(user);


        UserBO userBO = UserMapper.INSTANCE.user2UserBO(user);
        System.out.println(userBO);
    }


    public static void main(String[] args) {
        fun();
    }
}
