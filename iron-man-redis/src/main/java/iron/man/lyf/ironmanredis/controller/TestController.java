package iron.man.lyf.ironmanredis.controller;

import iron.man.lyf.ironmanredis.cache.RedisCommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liuyanfei
 * @description
 * @date 2022/4/1 7:30 下午
 **/
@RestController
@RequestMapping("/user")
public class TestController {
    @Autowired
    private RedisCommonUtil redis;

    @GetMapping("test")
    public void test(){
        redis.set("lyf","123");
    }
}
