package ecommerce_test.ecommerce.infrastructure.mapper;

import ecommerce_test.ecommerce.domain.BrandDO;
import ecommerce_test.ecommerce.infrastructure.entity.BrandEntity;
import org.mapstruct.Mapper;

@Mapper
public interface BrandMapper {

    BrandDO brandEntityToBrandDO(BrandEntity brandEntity);

    BrandEntity brandDOtoBrandEntity(BrandDO brandDO);
}
