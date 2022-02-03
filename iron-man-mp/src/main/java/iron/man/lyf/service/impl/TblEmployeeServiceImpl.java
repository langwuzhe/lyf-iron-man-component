package iron.man.lyf.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import iron.man.lyf.dao.TblEmployeeDao;
import iron.man.lyf.entity.TblEmployee;
import iron.man.lyf.service.TblEmployeeService;
import org.springframework.stereotype.Service;

/**
 * (TblEmployee)表服务实现类
 *
 * @author lyf
 * @since 2022-01-30 23:23:36
 */
@Service("tblEmployeeService")
public class TblEmployeeServiceImpl extends ServiceImpl<TblEmployeeDao, TblEmployee> implements TblEmployeeService {


}