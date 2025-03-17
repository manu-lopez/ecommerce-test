package ecommerce_test.ecommerce.application.exception;

public class PriceNotFoundException extends RuntimeException {
    public PriceNotFoundException(String msg) {
        super(msg);
    }
}
