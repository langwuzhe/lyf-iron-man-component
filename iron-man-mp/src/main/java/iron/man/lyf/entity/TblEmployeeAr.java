package iron.man.lyf.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.ToString;

/**
 * (TblEmployee)实体类
 *
 * @author lyf
 * @since 2022-01-30 23:23:35
 */
@Data
@ToString
public class TblEmployeeAr extends Model<TblEmployeeAr> {
    private static final long serialVersionUID = 942016859678726290L;

    private Integer id;

    private String lastName;

    private String email;

    private String gender;

    private Integer age;

    /** 配置数据库是否存在此属性*/
    @TableField(exist = false)
    private String myName;

//-----------------------------------手动添加--------------------------------------------

}

