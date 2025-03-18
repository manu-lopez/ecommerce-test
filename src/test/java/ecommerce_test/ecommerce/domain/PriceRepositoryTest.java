package ecommerce_test.ecommerce.domain;

import ecommerce_test.ecommerce.domain.repository.PriceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static ecommerce_test.ecommerce.factory.PriceFactory.priceDOFactory;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PriceRepositoryTest {

    @Mock
    private PriceRepository priceRepository;

    @Test
    void testGetPriceListByParams() {
        LocalDateTime testDate = LocalDateTime.of(2023, 1, 1, 10, 0);
        Long productId = 35455L;
        Integer brandId = 1;
        
        List<PriceDO> expectedPrices = Arrays.asList(priceDOFactory(), priceDOFactory());

        when(priceRepository.getPriceListByParams(testDate, productId, brandId))
                .thenReturn(List.of(priceDOFactory(), priceDOFactory()));

        List<PriceDO> actualPrices = priceRepository.getPriceListByParams(testDate, productId, brandId);

        assertEquals(expectedPrices.size(), actualPrices.size());
        assertEquals(expectedPrices.stream().findFirst().get().getPriceId(), actualPrices.stream().findFirst().get().getPriceId());
        verify(priceRepository).getPriceListByParams(testDate, productId, brandId);
    }
}