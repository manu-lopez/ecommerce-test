package ecommerce_test.ecommerce.application.service;

import ecommerce_test.ecommerce.application.dto.PriceDTO;
import ecommerce_test.ecommerce.application.exception.PriceNotFoundException;
import ecommerce_test.ecommerce.application.mapper.PriceApplicationMapper;
import ecommerce_test.ecommerce.domain.PriceDO;
import ecommerce_test.ecommerce.domain.repository.PriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;

@Service
@RequiredArgsConstructor
public class PriceServiceImpl implements PriceService {

    private final PriceApplicationMapper priceApplicationMapper;
    private final PriceRepository priceRepository;

    @Override
    public PriceDTO getPriceFromParams(LocalDateTime priceDate, Long productId, Integer brandId) {
        return priceRepository.getPriceListByParams(priceDate, productId, brandId)
                .stream()
                .max(Comparator.comparing(PriceDO::getPriority))
                .map(priceApplicationMapper::priceDOToPriceDTO)
                .orElseThrow(() -> new PriceNotFoundException(
                        String.format("Price not found for product ID %s", productId)
                ));
    }
}
