package iron.man.lyf.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import iron.man.lyf.entity.TblEmployee;
import org.apache.ibatis.annotations.Mapper;

/**
 * (TblEmployee)表数据库访问层
 *
 * @author lyf
 * @since 2022-01-30 23:23:34
 */
@Mapper
public interface TblEmployeeDao extends BaseMapper<TblEmployee> {
}

