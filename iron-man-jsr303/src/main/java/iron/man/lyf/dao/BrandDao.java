package iron.man.lyf.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import iron.man.lyf.entity.BrandEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 品牌
 * 
 * @author leifengyang
 * @email leifengyang@gmail.com
 * @date 2019-10-01 21:08:49
 */
@Mapper
public interface BrandDao extends BaseMapper<BrandEntity> {
	
}
