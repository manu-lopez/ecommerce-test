package ecommerce_test.ecommerce.infrastructure.controller;

import ecommerce_test.ecommerce.application.exception.PriceNotFoundException;
import ecommerce_test.ecommerce.infrastructure.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class RestControllerExceptionHandler {

    @ExceptionHandler(PriceNotFoundException.class)
    protected ResponseEntity<ErrorResponse> priceNotFoundHandler(PriceNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(ex.getClass().getSimpleName(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class, IllegalArgumentException.class,
            MethodArgumentNotValidException.class, MissingServletRequestParameterException.class})
    protected ResponseEntity<ErrorResponse> badParamsHandler() {
        ErrorResponse error = new ErrorResponse("IncorrectParamsException", "Incorrect input params");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
