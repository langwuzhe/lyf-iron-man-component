package iron.man.lyf.dao;

import iron.man.lyf.entity.TableLogicTest;
import iron.man.lyf.mybatis.injector.mapper.MyBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * (TableLogicTest)表数据库访问层
 *
 * @author lyf
 * @since 2022-02-02 18:12:14
 */
@Mapper
public interface TableLogicTestDao extends MyBaseMapper<TableLogicTest> {
}

