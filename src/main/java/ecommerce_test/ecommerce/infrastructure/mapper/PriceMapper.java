package ecommerce_test.ecommerce.infrastructure.mapper;

import ecommerce_test.ecommerce.application.dto.PriceDTO;
import ecommerce_test.ecommerce.domain.PriceDO;
import ecommerce_test.ecommerce.infrastructure.entity.PriceEntity;
import ecommerce_test.ecommerce.infrastructure.response.PriceResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(uses = BrandMapper.class)
public interface PriceMapper {

    List<PriceDO> priceEntityListToPriceDOList(List<PriceEntity> priceEntityList);

    PriceResponse priceDTOToPriceResponse(PriceDTO priceDTOList);

}
