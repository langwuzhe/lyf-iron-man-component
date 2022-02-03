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
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Course {

    private String courseName;
    private int sortNo;
    private long id;

}
