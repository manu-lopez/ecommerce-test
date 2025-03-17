package ecommerce_test.ecommerce.domain.repository;

import ecommerce_test.ecommerce.domain.PriceDO;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepository {

    List<PriceDO> getPriceListByParams(LocalDateTime priceDate, Long productId, Integer brandId);

}
