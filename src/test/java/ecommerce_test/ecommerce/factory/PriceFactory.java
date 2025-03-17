package ecommerce_test.ecommerce.factory;

import ecommerce_test.ecommerce.application.dto.PriceDTO;
import ecommerce_test.ecommerce.domain.PriceDO;
import ecommerce_test.ecommerce.infrastructure.response.PriceResponse;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static ecommerce_test.ecommerce.factory.BrandFactory.brandDOFactory;

public class PriceFactory {
    static public PriceDO priceDOFactory() {
        return PriceDO.builder()
                .priceId(1L)
                .brand(brandDOFactory())
                .startDate(LocalDateTime.of(2020, 6, 14, 0, 0, 0))
                .endDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59))
                .productId(35455L)
                .priceList(1)
                .priority(1)
                .price(new BigDecimal("35.50"))
                .currency("EUR")
                .build();
    }

    static public PriceDTO priceDTOFactory() {
        return PriceDTO.builder()
                .productId(35455L)
                .brandId(1)
                .priceList(1)
                .startDate(LocalDateTime.of(2020, 6, 14, 0, 0, 0))
                .endDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59))
                .price(new BigDecimal("35.50"))
                .currency("EUR")
                .build();
    }

    static public PriceResponse priceResponseFactory() {
        return PriceResponse.builder()
                .productId(35455L)
                .brandId(1)
                .priceList(1)
                .startDate(LocalDateTime.of(2020, 6, 14, 0, 0, 0))
                .endDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59))
                .price(new BigDecimal("35.50"))
                .currency("EUR")
                .build();
    }
}
