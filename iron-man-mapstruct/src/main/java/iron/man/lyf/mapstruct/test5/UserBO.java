package iron.man.lyf.mapstruct.test5;

import lombok.*;

import java.io.Serializable;

/**
 * @author liuyanfei
 * @description
 * @date 2021/12/9 10:04 上午
 **/
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserBO implements Serializable {

    private static final long serialVersionUID = 7257164843346458694L;

    /**
     * 编号
     */
    private Long id;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 大头相
     */
    private String avatar;

    /**
     * 邮编
     */
    private String email;

    /**
     * 微信
     */
    private String wechat;
}

