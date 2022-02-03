package iron.man.lyf.service;

import com.baomidou.mybatisplus.extension.service.IService;
import iron.man.lyf.entity.BrandEntity;


/**
 */
public interface BrandService extends IService<BrandEntity> {
    void updateDetail(BrandEntity brand);

}

