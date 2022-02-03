package iron.man.lyf.controller;

import iron.man.lyf.entity.TblEmployeeAr;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试 mp 的 ActiveRecord
 *
 * @author lyf
 * @since 2022-01-30 23:23:34
 */
@RestController
@RequestMapping("tblEmployeeControllerAr")
public class TblEmployeeControllerAr {


    @GetMapping("/queryByid")
    public TblEmployeeAr queryByid() {

        TblEmployeeAr tblEmployeeAr = new TblEmployeeAr();
        TblEmployeeAr tblEmployeeAr1 = tblEmployeeAr.selectById(1);
        System.out.println(tblEmployeeAr1);
        return tblEmployeeAr1;

    }

    @GetMapping("/save")
    public void save(){
        TblEmployeeAr employee = new TblEmployeeAr();
        employee.setLastName("宋老师");
        employee.setEmail("sls@atguigu.com");
        employee.setAge(35);

        boolean result = employee.insert();
        System.out.println("result:" +result );
    }



}

