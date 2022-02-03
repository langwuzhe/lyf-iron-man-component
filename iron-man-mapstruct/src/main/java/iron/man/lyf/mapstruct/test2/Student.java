package iron.man.lyf.mapstruct.test2;

import iron.man.lyf.mapstruct.test1.GenderEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author liuyanfei
 * @description
 * @date 2021/12/8 12:07 上午
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    private String name;
    private int age;
    private GenderEnum gender;
    private Double height;
    private Date birthday;

}