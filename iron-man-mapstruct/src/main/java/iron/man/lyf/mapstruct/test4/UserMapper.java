package iron.man.lyf.mapstruct.test4;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author liuyanfei
 * @description
 * @date 2021/12/8 10:57 下午
 **/
@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);


    // source 经过localDateTime2Str做进一步复杂的转换后 再交给target
    //localDateTime2Str 传入的 参数user.getCreateTime()为user2UserBO方法中的user
    @Mapping(target = "createTime", expression = "java(iron.man.lyf.mapstruct.test4.DateTransUtil.localDateTime2Str(user.getCreateTime()))")
    UserBO user2UserBO(User user);

}
