package ecommerce_test.ecommerce.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PriceDTO {

    private Long productId;

    private Long brandId;

    private Integer priceList;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private BigDecimal price;

    private String currency;
}
