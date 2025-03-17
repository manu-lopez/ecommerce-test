package ecommerce_test.ecommerce.infrastructure.controller;

import ecommerce_test.ecommerce.application.dto.PriceDTO;
import ecommerce_test.ecommerce.application.service.PriceService;
import ecommerce_test.ecommerce.infrastructure.mapper.PriceMapper;
import ecommerce_test.ecommerce.infrastructure.response.PriceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PriceController {

    private final PriceService priceService;

    private final PriceMapper mapper;

    @GetMapping("/price")
    public ResponseEntity<PriceResponse> getPrice(@RequestParam("priceDate") @DateTimeFormat(pattern = "yyyy-MM-dd-HH.mm.ss") LocalDateTime priceDate,
                                                  @RequestParam("productId") Long productId,
                                                  @RequestParam("brandId") Integer brandId) {
        final PriceDTO priceDTO = priceService.getPriceFromParams(priceDate, productId, brandId);
        return new ResponseEntity<>(mapper.priceDTOToPriceResponse(priceDTO), HttpStatus.OK);
    }
}
