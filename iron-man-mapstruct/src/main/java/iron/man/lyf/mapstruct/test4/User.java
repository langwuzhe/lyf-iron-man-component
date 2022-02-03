package iron.man.lyf.mapstruct.test4;

import lombok.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author liuyanfei
 * @description
 * @date 2021/12/8 10:56 下午
 **/
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    /**
     * 编号
     */
    private Long id;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

}

