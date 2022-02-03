package iron.man.lyf.mapstruct.test5;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * @author liuyanfei
 * @description
 * @date 2021/12/9 10:05 上午
 **/
@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    //方法一: 直接使用参数返回的方式
    PageBean<UserBO> user2UserBO(PageBean<User> user);

    //方法二:使用注解，标注，target
    void user2UserBO(PageBean<User> userPageBean, @MappingTarget PageBean<UserBO> userBOPageBean);
}

