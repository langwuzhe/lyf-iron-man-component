package iron.man.lyf.mapstruct.test5;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * @author liuyanfei
 * @description
 * @date 2021/12/9 10:03 上午
 **/
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 6357528845064191241L;

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
     * 登录名
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 性别   1男  2女  3未知
     */
    private Byte gender;

    /**
     * 电话
     */
    private String tel;

    /**
     * 邮编
     */
    private String email;

    /**
     * QQ
     */
    private String qq;

    /**
     * 微信
     */
    private String wechat;

    /**
     * 盐值
     */
    private String salt;

    /**
     * 备注
     */
    private String info;

    /**
     * 状态
     */
    private Byte status;

    /**
     * 注册时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}

