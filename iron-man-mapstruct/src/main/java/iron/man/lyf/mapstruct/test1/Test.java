package iron.man.lyf.mapstruct.test1;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author liuyanfei
 * @description
 * @date 2021/12/9 10:56 上午
 **/
public class Test {
    public static void main(String[] args) {
        //利用lombok 的建造者模式 创建一个实体对象
        Student student = Student.builder().name("小明").age(6).gender(GenderEnum.Male).height(121.1).birthday(new Date()).build();
        System.out.println(student);


        //单个实体对象转换   student --> studentVO
        StudentVO studentVO = StudentMapper.INSTANCE.student2StudentVO(student);
        System.out.println(studentVO);




        //集合类型 的转换 List<student> --> List<studentVO>、
        List<Student> list = new ArrayList<>();
        list.add(student);
        List<StudentVO> result = StudentMapper.INSTANCE.students2StudentVOs(list);
        System.out.println(result);

    }
}
