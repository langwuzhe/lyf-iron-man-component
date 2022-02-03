package iron.man.lyf.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * (TblEmployee)实体类
 *
 * @author lyf
 * @since 2022-01-30 23:23:35
 */
@Data
@ToString
/**设置实体类对应的表名*/ //已通过全局设置
//@TableName(value = "tbl_employee")
public class TblEmployee implements Serializable {
    private static final long serialVersionUID = 942016859678726290L;

    /**value 是对应数据库的列名，type 是主键类型，这里使用数据库策略*/
    @TableId(value = "id",type= IdType.AUTO)
    private Integer id;

    /** 设置属性对应的数据库表的属性*///已通过全局设置
//    @TableField(value = "last_name")
    private String lastName;


    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String email;


    private String gender;

    private Integer age;

    /** 配置数据库是否存在此属性*/
    @TableField(exist = false)
    private String myName;

//-----------------------------------手动添加--------------------------------------------

}

