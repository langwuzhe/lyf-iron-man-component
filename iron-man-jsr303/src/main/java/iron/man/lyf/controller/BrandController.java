package iron.man.lyf.controller;

import iron.man.lyf.entity.BrandEntity;
import iron.man.lyf.service.BrandService;
import iron.man.lyf.utils.R;
import iron.man.lyf.valid.AddGroup;
import iron.man.lyf.valid.UpdateGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


/**
 * 品牌
 */
@RestController
@RequestMapping("product/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;




    /**
     *
     * 测试数据：
     * {
     *     "brandId":"16",
     *     "name": "1234",
     *     "logo": "http://www.baidu.com",
     *     "descript": "",
     *     "showStatus": "1",
     *     "firstLetter": "w",
     *     "sort": 1
     *   }
     *
     *
     *
     *
     *
     * 不指定校验组 的  保存
     *
     * 如果controller 方法默认没有指定分组的，那么在 bean 中指定了分组的校验是不生效的。
     * 此校验中  只有descript 属性校验生效，其他指定了分组的校验不生效
     */
    @RequestMapping("/saveNoGroup")
    public R saveNotValidated(@Validated @RequestBody BrandEntity brand){

        brandService.save(brand);


        return R.ok();
    }


    /**
     * 请求数据
     *
     *
     *
     * {
     *     "brandId":"16",
     *     "name": "1234",
     *     "logo": "http://www.baidu.com",
     *     "descript": "",
     *     "showStatus": "1",
     *     "firstLetter": "w",
     *     "sort": 1
     *   }
     *
     *
     *
     * controller 添加 @Validated。给校验的bean后紧跟一个BindingResult，就可以获取到校验的结果
     * 自己捕获处理了异常，所以就不使用 @RestControllerAdvice 来处理异常了
     * @param brand
     * @param result
     * @return
     */
    @RequestMapping("/saveSingle")
    public R saveSingle(@Validated @RequestBody BrandEntity brand, BindingResult result){
        if(result.hasErrors()){
            Map<String,String> map = new HashMap<>();
            //1、获取校验的错误结果
            result.getFieldErrors().forEach((item)->{
                //FieldError 获取到错误提示
                String message = item.getDefaultMessage();
                //获取错误的属性的名字
                String field = item.getField();
                map.put(field,message);
            });

            return R.error(400,"提交的数据不合法").put("data",map);
        }else {

        }

        brandService.save(brand);


        return R.ok();
    }


    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@Validated({AddGroup.class}) @RequestBody BrandEntity brand){

        brandService.save(brand);


        return R.ok();
    }

    /**
     * 测试数据：
     * {
     *     "brandId":"1",
     *     "name": "1234",
     *     "logo": "http://www.baidu.com",
     *     "descript": "",
     *     "showStatus": "1",
     *     "firstLetter": "w",
     *     "sort": ""
     *   }
     *
     *
     *
     *
     *
     * 在 controller 中指定了分组，那么 bean 中没有指定分组或分组不同 的属性是不生效的
     *
     * 此指定了 UpdateGroup 分组，bean 中没有指定分组的descript 和 指定了AddGroup的 sort属性验证是不生效的
     */
    @RequestMapping("/update")
    public R update(@Validated(UpdateGroup.class) @RequestBody BrandEntity brand){
		brandService.updateDetail(brand);

        return R.ok();
    }


}
