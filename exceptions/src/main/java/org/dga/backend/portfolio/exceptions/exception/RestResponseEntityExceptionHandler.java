package org.dga.backend.portfolio.exceptions.exception;

import jakarta.annotation.PostConstruct;
import org.dga.backend.portfolio.exceptions.exception.internal.ExampleValidationException;
import org.hibernate.QueryTimeoutException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class RestResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {

    private Map<Integer, String> getMessagesCodeError = new HashMap<>();
    @PostConstruct
    private void init(){
        getMessagesCodeError.put(100, "Review a null pointer.");
        getMessagesCodeError.put(200, "It is not possible to connect to DB due to a connectivity problem. Check that BBDD has service available.");
        getMessagesCodeError.put(300, "An uncontrolled error has occurred in the execution. Check traces for more information.");
        getMessagesCodeError.put(400, "dateRequest must be less than or equal to the date of day.");
        getMessagesCodeError.put(401, "At least one of customerId or customerInternalId must be reported.");

    }
    @ExceptionHandler(value = { QueryTimeoutException.class})
    protected ResponseEntity<Object> handleErrorTimeoutBBDD(QueryTimeoutException e) {
        return new ResponseEntity<>(createRestException(e, 200), HttpStatus.REQUEST_TIMEOUT);
    }
    @ExceptionHandler(value = { NullPointerException.class})
    protected ResponseEntity<Object> handleErrorNullPointer(NullPointerException e) {
        return new ResponseEntity<>(createRestException(e, 100), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(value = { Exception.class})
    protected ResponseEntity<Object> handleErrorExceptionGeneral(Exception e) {
        return new ResponseEntity<>(createRestException(e, 300), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(value = { ExampleValidationException.class})
    protected ResponseEntity<Object> handleErrorValidationExceptionGeneral(ExampleValidationException e) {
        return new ResponseEntity<>(createRestException(e, e.getErrorCode()), HttpStatus.BAD_REQUEST);
    }
    private ExampleExceptionRest createRestException(Exception e, Integer codeError){
        ExampleExceptionRest exc = new ExampleExceptionRest();
        exc.setError(codeError);
        exc.setDescription(getMessagesCodeError.get(codeError));
        exc.setException(e.getClass().getCanonicalName());
        exc.setExceptionMessage(e.getMessage());
        exc.setClassProducedError(Arrays.stream(e.getStackTrace()).findFirst().get().getClassName());
        exc.setMethodProducedError(Arrays.stream(e.getStackTrace()).findFirst().get().getMethodName());
        exc.setLineMethodProducedError(Arrays.stream(e.getStackTrace()).findFirst().get().getLineNumber());
        logger.error("Throw next exception: {}",exc);
        return exc;
    }
}
