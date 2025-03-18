package ecommerce_test.ecommerce.integration;

import ecommerce_test.ecommerce.application.exception.PriceNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RestControllerExceptionHandlerIT {

    public static final String $_ERROR_PATH = "$.error";
    public static final String $_MSG_PATH = "$.msg";
    public static final String INCORRECT_PARAMS_EXCEPTION = "IncorrectParamsException";
    public static final String INCORRECT_INPUT_PARAMS = "Incorrect input params";

    @Autowired
    private MockMvc mockMvc;

    @TestConfiguration
    static class TestConfig {
        @Bean
        public TestController testController() {
            return new TestController();
        }
    }

    // Controlador ficticio para probar el manejo de excepciones
    @RestController
    static class TestController {

        @GetMapping("/test/price-not-found")
        public void throwPriceNotFoundException() {
            throw new PriceNotFoundException("Price not found");
        }

        @GetMapping("/test/bad-param")
        public void throwIllegalArgumentException() {
            throw new IllegalArgumentException("Bad parameter");
        }

        @GetMapping("/test/type-mismatch")
        public void throwMethodArgumentTypeMismatchException() {
            throw mock(MethodArgumentTypeMismatchException.class);
        }

        @GetMapping("/test/missing-param")
        public void throwMissingServletRequestParameterException() throws MissingServletRequestParameterException {
            throw mock(MissingServletRequestParameterException.class);
        }
    }

    @Test
    void whenPriceNotFound_thenReturnNotFoundStatus() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/test/price-not-found")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath($_ERROR_PATH, is("PriceNotFoundException")))
                .andExpect(jsonPath($_MSG_PATH, is("Price not found")));
    }

    @Test
    void whenIllegalArgumentException_thenReturnBadRequestStatus() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/test/bad-param")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath($_ERROR_PATH, is(INCORRECT_PARAMS_EXCEPTION)))
                .andExpect(jsonPath($_MSG_PATH, is(INCORRECT_INPUT_PARAMS)));
    }

    @Test
    void whenMethodArgumentTypeMismatch_thenReturnBadRequestStatus() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/test/type-mismatch")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath($_ERROR_PATH, is(INCORRECT_PARAMS_EXCEPTION)))
                .andExpect(jsonPath($_MSG_PATH, is(INCORRECT_INPUT_PARAMS)));
    }

    @Test
    void whenMissingServletRequestParameter_thenReturnBadRequestStatus() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/test/missing-param")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath($_ERROR_PATH, is(INCORRECT_PARAMS_EXCEPTION)))
                .andExpect(jsonPath($_MSG_PATH, is(INCORRECT_INPUT_PARAMS)));
    }
}