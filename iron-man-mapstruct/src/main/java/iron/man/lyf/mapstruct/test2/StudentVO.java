package iron.man.lyf.mapstruct.test2;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liuyanfei
 * @description
 * @date 2021/12/8 12:07 上午
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
    private String course;
}