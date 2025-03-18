package ecommerce_test.ecommerce.integration;


import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.UriComponentsBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PriceControllerIT {

    public static final String PRODUCT_ID = "35455";
    public static final String BRAND_ID = "1";
    public static final String PRODUCT_PRICE_PATH = "$.product_price";

    @Autowired
    private MockMvc mockMvc;

    private String buildUri(String priceDate, String productId, String brandId) {
        return UriComponentsBuilder.fromPath("/api/price")
                .queryParam("priceDate", priceDate)
                .queryParam("productId", productId)
                .queryParam("brandId", brandId)
                .toUriString();
    }

    // Test 1: petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA)
    @Test
    public void givenGetPrice1_WhenPriceExist_ThenResultOK() throws Exception {
        mockMvc.perform(get(this.buildUri("2020-06-14-10.00.00", PRODUCT_ID, BRAND_ID))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath(PRODUCT_PRICE_PATH, Is.is(35.50)));
    }

    // Test 2: petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA)
    @Test
    public void givenGetPrice2_WhenPriceExist_ThenResultOK() throws Exception {
        mockMvc.perform(get(this.buildUri("2020-06-14-16.00.00", PRODUCT_ID, BRAND_ID))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath(PRODUCT_PRICE_PATH, Is.is(25.45)));
    }

    // Test 3: petición a las 21:00 del día 14 del producto 35455 para la brand 1 (ZARA)
    @Test
    public void givenGetPrice3_WhenPriceExist_ThenResultOK() throws Exception {
        mockMvc.perform(get(this.buildUri("2020-06-14-21.00.00", PRODUCT_ID, BRAND_ID))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath(PRODUCT_PRICE_PATH, Is.is(35.50)));
    }

    // Test 4: petición a las 10:00 del día 15 del producto 35455 para la brand 1 (ZARA)
    @Test
    public void givenGetPrice4_WhenPriceExist_ThenResultOK() throws Exception {
        mockMvc.perform(get(this.buildUri("2020-06-15-10.00.00", PRODUCT_ID, BRAND_ID))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath(PRODUCT_PRICE_PATH, Is.is(30.50)));
    }

    // Test 5: petición a las 21:00 del día 16 del producto 35455 para la brand 1 (ZARA)
    @Test
    public void givenGetPrice5_WhenPriceExist_ThenResultOK() throws Exception {
        mockMvc.perform(get(this.buildUri("2020-06-16-21.00.00", PRODUCT_ID, BRAND_ID))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath(PRODUCT_PRICE_PATH, Is.is(38.95)));
    }

    @Test
    public void givenGetPrice_WhenPriceDoesNotExist_ThenResultIsNotFound() throws Exception {
        mockMvc.perform(get(this.buildUri("2020-06-16-21.00.00", "1", "2"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}
