package iron.man.lyf.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import iron.man.lyf.entity.TblEmployee;
import iron.man.lyf.service.TblEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * (TblEmployee)表控制层
 *
 * @author lyf
 * @since 2022-01-30 23:23:34
 */
@RestController
@RequestMapping("tblEmployee")
public class TblEmployeeController {
    /**
     * 服务对象
     */
    @Autowired
    private TblEmployeeService tblEmployeeService;


    /**
     * 小试牛刀
     * @return
     */
    @GetMapping("/queryByid")
    public TblEmployee queryByid() {


        TblEmployee tblEmployee = tblEmployeeService.getById(1);
        return tblEmployee;

    }

    /**
     * 分页  配合 在MyBatisConfig 中的分页配置
     * @return
     */
    @GetMapping("/queryByPage")
    public IPage queryByPage() {

        Page<TblEmployee> objectPage = new Page<>(2, 2);
        IPage<TblEmployee> page = tblEmployeeService.page(objectPage,new QueryWrapper<TblEmployee>());
        return page;

    }

    /**
     * 测试  mp 自动返回插入的主键
     * @return
     */
    @GetMapping("/save")
    public int save(){
        TblEmployee tblEmployee = new TblEmployee();
        tblEmployee.setEmail("123");
        tblEmployee.setGender("1");
        tblEmployee.setLastName("234");
        tblEmployee.setAge(1);

        tblEmployeeService.save(tblEmployee);

        //mybatis-plus 会自动把插入的 id 返回


        System.out.println(tblEmployee.getId());
        return tblEmployee.getId();

    }


    @GetMapping("/del")
    public void del(){
        tblEmployeeService.remove(null);
    }


    /**
     * 条件构造器
     */
    @GetMapping("/getList")
    public  void getList(){

//        sql:SELECT id,last_name,gender,age,email
//            FROM tbl_employee
//            WHERE (last_name = 'Tom' AND age BETWEEN 18 AND 50 AND email LIKE '%a%') ORDER BY age DESC;
        QueryWrapper<TblEmployee> queryWrapper =
                new QueryWrapper<TblEmployee>()
                        .eq("last_name", "Tom")
                        .between("age", 18, 50)
                        .like("email", "a")
                        .orderByDesc("age");


        TblEmployee one = tblEmployeeService.getOne(queryWrapper);

        System.out.println(one);
    }


}

