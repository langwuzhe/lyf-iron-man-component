package iron.man.lyf.mybatis.injector.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author lyf
 * @description
 * @date 2022/2/2 6:40 下午
 **/
public interface MyBaseMapper<T> extends BaseMapper<T> {

    List<T> selectListNoLogic();

    // 扩展其他的方法

}

