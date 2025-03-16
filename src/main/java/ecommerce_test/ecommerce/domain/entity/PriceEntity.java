package ecommerce_test.ecommerce.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="PRICES")
@Getter
@Setter
public class PriceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PRICE_ID", nullable = false, unique = true)
    private Long priceId;

    @ManyToOne
    @JoinColumn(name = "BRAND_ID", nullable = false)
    private BrandEntity brand;

    @Column(name = "START_DATE", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "END_DATE", nullable = false)
    private LocalDateTime endDate;

    @Column(name = "PRICE_LIST", nullable = false)
    private Integer priceList;

    @Column(name = "PRODUCT_ID", nullable = false)
    private Long productId;

    @Column(name = "PRIORITY", nullable = false)
    private Integer priority;

    @Column(name = "PRICE", nullable = false)
    private BigDecimal price;

    @Column(name = "CURR", nullable = false, length = 3)
    private String currency;
}
