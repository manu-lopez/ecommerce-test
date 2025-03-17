package ecommerce_test.ecommerce.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class BrandDO {
    
    private Integer id;

    private String name;
}
