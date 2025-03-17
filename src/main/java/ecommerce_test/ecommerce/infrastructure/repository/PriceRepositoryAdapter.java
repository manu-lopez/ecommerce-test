package ecommerce_test.ecommerce.infrastructure.repository;

import ecommerce_test.ecommerce.domain.PriceDO;
import ecommerce_test.ecommerce.domain.repository.PriceRepository;
import ecommerce_test.ecommerce.infrastructure.entity.PriceEntity;
import ecommerce_test.ecommerce.infrastructure.mapper.PriceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PriceRepositoryAdapter implements PriceRepository {

    private final JpaPriceRepository priceRepository;

    private final PriceMapper priceMapper;

    @Override
    public List<PriceDO> getPriceListByParams(LocalDateTime priceDate, Long productId, Integer brandId) {
        Optional<List<PriceEntity>> priceEntityList = priceRepository.getPriceListByParams(priceDate, productId, brandId);
        return priceEntityList.map(priceMapper::priceEntityListToPriceDOList).orElse(List.of());
    }
}
