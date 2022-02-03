package iron.man.lyf.mybatis.injector.service;

import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author lyf
 * @description
 * @date 2022/2/2 9:18 下午
 **/
public interface IBaseService<T> extends IService<T> {
    public List<T> listNoLogic();
}
