package ecommerce_test.ecommerce.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="BRAND")
@Getter
@Setter
public class BrandEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="BRAND_ID", nullable = false, unique = true)
    private Integer id;

    @Column(name="BRAND_NAME", nullable = false, length = 100)
    private String name;
}
