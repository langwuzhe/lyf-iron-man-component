package iron.man.lyf.mapstruct.test3;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liuyanfei
 * @description
 * @date 2021/12/8 10:03 上午
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private Integer age;
    private String fullname;
}
