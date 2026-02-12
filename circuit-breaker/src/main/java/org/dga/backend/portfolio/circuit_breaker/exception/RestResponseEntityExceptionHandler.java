package org.dga.backend.portfolio.circuit_breaker.exception;

import jakarta.annotation.PostConstruct;
import org.dga.backend.portfolio.circuit_breaker.exception.internal.ErrorGetInformationException;
import org.dga.backend.portfolio.circuit_breaker.exception.internal.ErrorOpenCircuitBreakerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class RestResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {

    private Map<Integer, String> getMessagesCodeError = new HashMap<>();
    @PostConstruct
    private void init(){
        getMessagesCodeError.put(100, "An error ocurred when invoked a external services for request information by Id.");
        getMessagesCodeError.put(201, "Circuit Breaker operating for the invokation to the getInformation by Id external service.");
    }
    @ExceptionHandler(value = { ErrorGetInformationException.class})
    protected ResponseEntity<Object> handleErrorInvokationInformation(ErrorGetInformationException e) {
        return new ResponseEntity<>(createRestException(e, 100, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(value = { ErrorOpenCircuitBreakerException.class})
    protected ResponseEntity<Object> handleErrorCircuitBreaker(ErrorOpenCircuitBreakerException e) {
        return new ResponseEntity<>(createRestException(e, e.getCodeService(),
                "Status Circuit Breaker: " + e.getState() + " - Failure Rate: " + e.getFailureRate() + " - Description: " + e.getDetailError()),
                HttpStatus.SERVICE_UNAVAILABLE);
    }
    private CircuitBreakerExceptionRest createRestException(Exception e, Integer codeError, String detailError){
        CircuitBreakerExceptionRest exc = new CircuitBreakerExceptionRest();
        exc.setError(codeError);
        exc.setDescription(getMessagesCodeError.get(codeError));
        exc.setDetailError(detailError);
        exc.setException(e.getClass().getCanonicalName());
        logger.error("Throw next exception: {}",exc);
        return exc;
    }
}
