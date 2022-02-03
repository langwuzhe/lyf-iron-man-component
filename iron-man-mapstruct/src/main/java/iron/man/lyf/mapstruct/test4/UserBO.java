package iron.man.lyf.mapstruct.test4;

import lombok.*;

/**
 * @author liuyanfei
 * @description
 * @date 2021/12/8 10:56 下午
 **/
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserBO {
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
    private String createTime;
}

