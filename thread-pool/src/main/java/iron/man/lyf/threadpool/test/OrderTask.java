package iron.man.lyf.threadpool.test;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author liuyanfei
 * @description
 * @date 2022/3/26 2:18 上午
 **/


@Component
public class OrderTask {




    @Async("taskExecutor")
    public String orderInvalidation(String orderNumber){

        System.out.println(Thread.currentThread().getName()+":"+orderNumber);
        Thread.yield();
        System.out.println(Thread.currentThread().getName()+":"+orderNumber);

        return "";

    }
}
