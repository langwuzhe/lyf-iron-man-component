package iron.man.lyf.mybatis.fill;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import iron.man.lyf.entity.TblEmployee;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * 填充 @TableField(fill = FieldFill.INSERT_UPDATE)  做默认填充
 * @author lyf
 * @description
 * @date 2022/2/3 11:24 上午
 **/
@Log4j2
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        Object originalObject = metaObject.getOriginalObject();
        //如果是  TblEmployee 表的调用才生效
        if(originalObject instanceof TblEmployee){
            this.strictInsertFill(metaObject, "email", () -> "www.baidu.com", String.class);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        // 起始版本 3.3.3(推荐)
        this.strictUpdateFill(metaObject, "email", () -> "www.baidu.com", String.class);
    }
}
