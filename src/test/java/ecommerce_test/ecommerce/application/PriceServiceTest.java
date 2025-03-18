package ecommerce_test.ecommerce.application;

import ecommerce_test.ecommerce.application.dto.PriceDTO;
import ecommerce_test.ecommerce.application.exception.PriceNotFoundException;
import ecommerce_test.ecommerce.application.mapper.PriceApplicationMapper;
import ecommerce_test.ecommerce.application.service.PriceServiceImpl;
import ecommerce_test.ecommerce.domain.PriceDO;
import ecommerce_test.ecommerce.domain.repository.PriceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static ecommerce_test.ecommerce.factory.PriceFactory.priceDOFactory;
import static ecommerce_test.ecommerce.factory.PriceFactory.priceDTOFactory;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PriceServiceTest {

    @InjectMocks
    private PriceServiceImpl priceService;

    @Mock
    private PriceRepository priceRepository;

    @Mock
    private PriceApplicationMapper priceMapper;

    private final LocalDateTime TEST_DATE = LocalDateTime.of(2023, 6, 14, 10, 0, 0);
    private final Long PRODUCT_ID = 35455L;
    private final Integer BRAND_ID = 1;

    @Test
    public void givenGetPriceFromParams_whenListWithData_thenReturnPriceDTO() {
        List<PriceDO> priceDOList = new ArrayList<>();
        priceDOList.add(priceDOFactory());
        PriceDTO expectedDTO = priceDTOFactory();

        when(this.priceRepository.getPriceListByParams(TEST_DATE, PRODUCT_ID, BRAND_ID)).thenReturn(priceDOList);
        when(this.priceMapper.priceDOToPriceDTO(priceDOList.get(0))).thenReturn(expectedDTO);

        PriceDTO result = this.priceService.getPriceFromParams(TEST_DATE, PRODUCT_ID, BRAND_ID);

        verify(this.priceRepository, times(1)).getPriceListByParams(TEST_DATE, PRODUCT_ID, BRAND_ID);
        verify(this.priceMapper, times(1)).priceDOToPriceDTO(priceDOList.get(0));
        assertEquals(expectedDTO, result);
    }

    @Test
    void givenGetPriceFromParams_whenListIsEmpty_thenThrowError() {

        List<PriceDO> priceDOList = new ArrayList<>();

        when(this.priceRepository.getPriceListByParams(TEST_DATE, PRODUCT_ID, BRAND_ID)).thenReturn(priceDOList);

        assertThrows(PriceNotFoundException.class, () -> this.priceService.getPriceFromParams(TEST_DATE, PRODUCT_ID, BRAND_ID));
        verify(this.priceRepository, times(1)).getPriceListByParams(TEST_DATE, PRODUCT_ID, BRAND_ID);
        verify(this.priceMapper, times(0)).priceDOToPriceDTO(any(PriceDO.class));
    }
}
