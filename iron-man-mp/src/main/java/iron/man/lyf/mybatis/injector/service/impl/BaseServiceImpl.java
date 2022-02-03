package iron.man.lyf.mybatis.injector.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import iron.man.lyf.mybatis.injector.mapper.MyBaseMapper;
import iron.man.lyf.mybatis.injector.service.IBaseService;

import java.util.List;

/**
 *
 * 第一个泛型 要继承 自定义的  MyBaseMapper
 * @author lyf
 * @description
 * @date 2022/2/2 9:14 下午
 **/
public class BaseServiceImpl<M extends MyBaseMapper<T>, T> extends ServiceImpl<M, T>  implements IBaseService<T> {

    @Override
    public List<T> listNoLogic() {
        return this.baseMapper.selectListNoLogic();
    }
}
