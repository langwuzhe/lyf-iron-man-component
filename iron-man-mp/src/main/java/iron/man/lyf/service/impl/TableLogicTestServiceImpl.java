package iron.man.lyf.service.impl;

import iron.man.lyf.dao.TableLogicTestDao;
import iron.man.lyf.entity.TableLogicTest;
import iron.man.lyf.mybatis.injector.service.impl.BaseServiceImpl;
import iron.man.lyf.service.TableLogicTestService;
import org.springframework.stereotype.Service;

/**
 * (TableLogicTest)表服务实现类
 *
 * @author lyf
 * @since 2022-02-02 18:12:16
 */
@Service("tableLogicTestService")
public class TableLogicTestServiceImpl extends BaseServiceImpl<TableLogicTestDao,TableLogicTest> implements TableLogicTestService  {
}
