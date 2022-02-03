package iron.man.lyf.mapstruct.test1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 要转换的源对象  source
 * @author liuyanfei
 * @description
 * @date 2021/12/7 9:51 下午
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