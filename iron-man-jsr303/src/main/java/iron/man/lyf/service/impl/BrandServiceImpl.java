package iron.man.lyf.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import iron.man.lyf.dao.BrandDao;
import iron.man.lyf.entity.BrandEntity;
import iron.man.lyf.service.BrandService;
import org.springframework.stereotype.Service;


@Service("brandService")
public class BrandServiceImpl extends ServiceImpl<BrandDao, BrandEntity> implements BrandService {
    @Override
    public void updateDetail(BrandEntity brand) {
        //保证冗余字段的数据一致
        this.updateById(brand);
    }

}