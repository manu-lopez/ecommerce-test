package ecommerce_test.ecommerce.factory;

import ecommerce_test.ecommerce.domain.BrandDO;

public class BrandFactory {
    static public BrandDO brandDOFactory() {
        return BrandDO.builder()
                .id(1)
                .name("ZARA")
                .build();
    }
}

