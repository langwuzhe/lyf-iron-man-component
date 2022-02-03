package iron.man.lyf.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;

/**
 * (TableLogicTest)实体类
 *
 * @author lyf
 * @since 2022-02-02 18:12:15
 */
@Data
public class TableLogicTest implements Serializable {
    private static final long serialVersionUID = -27293226910604039L;

    private Integer id;

    private String name;
    /**
     * 逻辑删除标识。value 未删除的标识，delval 删除的标识
     */
    @TableLogic(value = "2",delval = "3")
    private Integer flag;

//-----------------------------------手动添加--------------------------------------------

}

