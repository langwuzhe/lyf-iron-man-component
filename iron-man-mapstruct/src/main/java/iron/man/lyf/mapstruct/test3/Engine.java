package iron.man.lyf.mapstruct.test3;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liuyanfei
 * @description
 * @date 2021/12/8 10:04 上午
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Engine {
    private Integer horsePower;
    private Integer fuel;
}
