package agent.application.productservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ Exception.class })
    public final ResponseEntity<ErrorMessage> handleException(Exception ex, WebRequest request) {
        ErrorMessage retVal = new ErrorMessage( ex.getMessage(), HttpStatus.BAD_REQUEST.value(), LocalDateTime.now());
        if (ex instanceof IllegalArgumentException || ex instanceof ImageStorageException) {
            retVal.setStatus(HttpStatus.BAD_REQUEST.value());
        } 
        return ResponseEntity.status(retVal.getStatus()).body(retVal);
    }

}
