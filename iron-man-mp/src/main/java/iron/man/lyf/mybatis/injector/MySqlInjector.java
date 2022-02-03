package iron.man.lyf.mybatis.injector;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import iron.man.lyf.mybatis.injector.method.SelectListNoLogic;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 注入器
 * @author lyf
 * @description
 * @date 2022/2/2 6:35 下午
 **/
@Component
public class MySqlInjector extends DefaultSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass, TableInfo tableInfo) {
        List<AbstractMethod> methodList = new ArrayList<>();

        // 获取父类中的集合，把父类的方法先加载
        methodList.addAll(super.getMethodList(mapperClass,tableInfo));



        // 再扩充加载自定义的方法，定义了几个，就加载几个
        methodList.add(new SelectListNoLogic());

        return methodList;
    }
}
