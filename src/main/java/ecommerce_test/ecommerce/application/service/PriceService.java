package ecommerce_test.ecommerce.application.service;

import ecommerce_test.ecommerce.application.dto.PriceDTO;

import java.time.LocalDateTime;

public interface PriceService {

    PriceDTO getPriceFromParams(LocalDateTime priceDate, Long productId, Integer brandId);

}
