package iron.man.lyf.controller;

import iron.man.lyf.entity.TableLogicTest;
import iron.man.lyf.service.TableLogicTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * (TableLogicTest)表控制层
 *
 * @author lyf
 * @since 2022-02-02 18:12:14
 */
@RestController
@RequestMapping("tableLogicTest")
public class TableLogicTestController {
    @Autowired
    TableLogicTestService tableLogicTestService;


    /**
     * 测试逻辑删除的  查询
     * @return
     */
    @GetMapping("/queryByid")
    public TableLogicTest queryByid() {


        TableLogicTest tableLogicTest = tableLogicTestService.getById(1);
        return tableLogicTest;

    }

    /**
     * 测试 自定义的 注入 sql
     * @return
     */
    @GetMapping("/selectListNoLogic")
    public List<TableLogicTest> selectListNoLogic() {


        List<TableLogicTest> all = tableLogicTestService.listNoLogic();
        return all;

    }


    @GetMapping("/selectList")
    public List<TableLogicTest> selectList() {


        List<TableLogicTest> all = tableLogicTestService.list();
        return all;

    }



}

