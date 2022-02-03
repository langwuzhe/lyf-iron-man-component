package iron.man.lyf.mapstruct.test2;

import iron.man.lyf.mapstruct.test1.GenderEnum;

import java.util.Date;

/**
 * @author liuyanfei
 * @description
 * @date 2021/12/9 11:22 上午
 **/
public class Test {
    public static void main(String[] args) {

        Student student = Student.builder().name("小明").age(6).gender(GenderEnum.Male).height(121.1).birthday(new Date()).build();
        Course course = Course.builder().id(1L).courseName("语文").build();


        //把student, course 两个对象  合并成  StudentVO
        StudentVO studentVO = StudentMapper.INSTANCE.studentAndCourse2StudentVO(student, course);
        System.out.println(studentVO);
    }
}
