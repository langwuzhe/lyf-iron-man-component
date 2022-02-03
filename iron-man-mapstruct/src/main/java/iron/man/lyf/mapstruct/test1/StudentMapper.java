package iron.man.lyf.mapstruct.test1;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author liuyanfei
 * @description
 * @date 2021/12/7 9:54 下午
 **/
@Mapper
public interface StudentMapper {

    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);


    /**
     * 下面的两个 mapping  为student2StudentVO、students2StudentVOs 两个方法的转换规则
     */
    //gender.name 为  从 source的student对象中，取出来枚举类型gender 的 name 值
    @Mapping(source = "gender.name", target = "gender")

    //把student 对象的类型为Date 的 birthday   转为  对象 StudentVO 类型为 String 的birthday
    @Mapping(source = "birthday", target = "birthday", dateFormat = "yyyy-MM-dd HH:mm:ss")
    StudentVO student2StudentVO(Student student);


    //集合和集合之间的转换，使用上面的两个 mapping 规则
    List<StudentVO> students2StudentVOs(List<Student> studentList);

}