package ecommerce_test.ecommerce.application.service;

import ecommerce_test.ecommerce.application.dto.PriceDTO;
import ecommerce_test.ecommerce.application.mapper.PriceApplicationMapper;
import ecommerce_test.ecommerce.domain.PriceDO;
import ecommerce_test.ecommerce.domain.repository.PriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PriceServiceImpl implements PriceService {

    private final PriceApplicationMapper priceApplicationMapper;
    private final PriceRepository priceRepository;

    @Override
    public PriceDTO getPriceFromParams(LocalDateTime priceDate, Long productId, Integer brandId) {
        final List<PriceDO> priceDOList = priceRepository.getPriceListByParams(priceDate, productId, brandId);

        // Todo: Throw controlled exception
        if (priceDOList.isEmpty()) {
            return null;
        }

        priceDOList.sort(Comparator.comparing(PriceDO::getPriority).reversed());

        return priceApplicationMapper.priceDOToPriceDTO(priceDOList.get(0));
    }
}
