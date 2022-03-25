package iron.man.lyf.threadpool.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liuyanfei
 * @description
 * @date 2022/3/26 2:20 上午
 **/
@RestController
@RequestMapping("TestController")
public class TestController {

    @Autowired
    private OrderTask orderTask;


    @GetMapping("/test")
    public void test(){
        for (int i=0;i<10;i++){

            orderTask.orderInvalidation(String.valueOf(i));
        }
    }

}
