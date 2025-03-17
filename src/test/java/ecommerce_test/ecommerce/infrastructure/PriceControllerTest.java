package ecommerce_test.ecommerce.infrastructure;

import ecommerce_test.ecommerce.application.dto.PriceDTO;
import ecommerce_test.ecommerce.application.exception.PriceNotFoundException;
import ecommerce_test.ecommerce.application.service.PriceService;
import ecommerce_test.ecommerce.infrastructure.controller.PriceController;
import ecommerce_test.ecommerce.infrastructure.mapper.PriceMapper;
import ecommerce_test.ecommerce.infrastructure.response.PriceResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static ecommerce_test.ecommerce.factory.PriceFactory.priceDTOFactory;
import static ecommerce_test.ecommerce.factory.PriceFactory.priceResponseFactory;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PriceControllerTest {

    @Mock
    private PriceService priceService;

    @Mock
    private PriceMapper mapper;

    @InjectMocks
    private PriceController priceController;

    private final LocalDateTime TEST_DATE = LocalDateTime.of(2023, 6, 14, 10, 0, 0);
    private final Long PRODUCT_ID = 35455L;
    private final Integer BRAND_ID = 1;
    private final PriceDTO PRICE_DTO = priceDTOFactory();
    private final PriceResponse PRICE_RESPONSE = priceResponseFactory();

    @Test
    void givenGetPrice_whenValidParameters_thenReturnOkResponse() {
        when(priceService.getPriceFromParams(TEST_DATE, PRODUCT_ID, BRAND_ID)).thenReturn(PRICE_DTO);
        when(mapper.priceDTOToPriceResponse(PRICE_DTO)).thenReturn(PRICE_RESPONSE);

        ResponseEntity<PriceResponse> response = priceController.getPrice(TEST_DATE, PRODUCT_ID, BRAND_ID);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(PRICE_RESPONSE, response.getBody());

        verify(priceService).getPriceFromParams(TEST_DATE, PRODUCT_ID, BRAND_ID);
        verify(mapper).priceDTOToPriceResponse(PRICE_DTO);
    }

    @Test
    void givenGetPrice_whenServiceThrowsException_thenPropagateException() {
        PriceNotFoundException exception = new PriceNotFoundException("Price not found for product ID " + PRODUCT_ID);
        when(priceService.getPriceFromParams(TEST_DATE, PRODUCT_ID, BRAND_ID)).thenThrow(exception);

        try {
            priceController.getPrice(TEST_DATE, PRODUCT_ID, BRAND_ID);
        } catch (PriceNotFoundException e) {
            assertEquals(exception.getMessage(), e.getMessage());
        }

        verify(priceService).getPriceFromParams(TEST_DATE, PRODUCT_ID, BRAND_ID);
        verifyNoInteractions(mapper);
    }
}
