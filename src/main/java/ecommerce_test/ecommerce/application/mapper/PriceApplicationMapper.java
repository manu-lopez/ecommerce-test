package ecommerce_test.ecommerce.application.mapper;

import ecommerce_test.ecommerce.application.dto.PriceDTO;
import ecommerce_test.ecommerce.domain.PriceDO;
import ecommerce_test.ecommerce.infrastructure.mapper.BrandMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = BrandMapper.class)
public interface PriceApplicationMapper {

    @Mapping(target = "brandId", source = "brand.id")
    PriceDTO priceDOToPriceDTO(PriceDO priceDO);
}
