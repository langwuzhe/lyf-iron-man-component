package iron.man.lyf.mapstruct.test1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 *  要转换的目标对象 target
 * @author liuyanfei
 * @description
 * @date 2021/12/7 9:54 下午
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentVO {
    private String name;
    private int age;
    private String gender;
    private Double height;
    private String birthday;
}