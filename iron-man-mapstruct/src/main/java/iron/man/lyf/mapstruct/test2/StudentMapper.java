package iron.man.lyf.mapstruct.test2;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author liuyanfei
 * @description
 * @date 2021/12/8 12:09 上午
 **/
@Mapper
public interface StudentMapper {

    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);


    //把student和course 对象的数据合并成  StudentVO
    //mapping 中的student、course 为 方法studentAndCourse2StudentVO入参的 student、course

    @Mapping(source = "student.gender.name", target = "gender")
    @Mapping(source = "student.birthday", target = "birthday", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(source = "course.courseName", target = "course")
    @Mapping(source = "student.name",  target = "name",  defaultValue = "张三")
    StudentVO studentAndCourse2StudentVO(Student student, Course course);

}
