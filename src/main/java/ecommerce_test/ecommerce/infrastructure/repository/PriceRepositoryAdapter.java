package ecommerce_test.ecommerce.infrastructure.repository;

import ecommerce_test.ecommerce.application.exception.PriceNotFoundException;
import ecommerce_test.ecommerce.domain.PriceDO;
import ecommerce_test.ecommerce.domain.repository.PriceRepository;
import ecommerce_test.ecommerce.infrastructure.entity.PriceEntity;
import ecommerce_test.ecommerce.infrastructure.mapper.PriceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Repository
@RequiredArgsConstructor
public class PriceRepositoryAdapter implements PriceRepository {

    private final JpaPriceRepository priceRepository;

    private final PriceMapper priceMapper;

    @Override
    public List<PriceDO> getPriceListByParams(LocalDateTime priceDate, Long productId, Integer brandId) {

        List<PriceEntity> priceEntityList;
        try {
            priceEntityList = priceRepository.getPriceListByParams(priceDate, productId, brandId).orElseThrow();
        } catch (NoSuchElementException e) {
            throw new PriceNotFoundException(String.format("Price not found for product ID %s", productId));
        } catch (Exception e) {
            throw new RuntimeException("Error while trying to get the price");
        }

        return priceMapper.priceEntityListToPriceDOList(priceEntityList);
    }
}
