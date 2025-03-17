package ecommerce_test.ecommerce.infrastructure.repository;

import ecommerce_test.ecommerce.infrastructure.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface JpaPriceRepository extends JpaRepository<PriceEntity, Long> {

    @Query("SELECT p FROM PriceEntity p WHERE p.brand.id = :brandId AND p.productId = :productId " +
            "AND :priceDate BETWEEN p.startDate and p.endDate " +
            "ORDER BY p.priority DESC, p.startDate ASC")
    Optional<List<PriceEntity>> getPriceListByParams(@Param("priceDate") LocalDateTime priceDate,
                                                     @Param("productId") Long productId,
                                                     @Param("brandId") Integer brandId);

}
