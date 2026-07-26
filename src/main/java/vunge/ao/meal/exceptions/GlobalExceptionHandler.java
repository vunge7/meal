package vunge.ao.meal.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import vunge.ao.meal.util.ApiErrors;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiErrors> handleNotFoundException(NotFoundException ex) {
        log.warn("NotFoundException: {}", ex.getMessage());
        var error = new ApiErrors(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                new Date()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ApiErrors> handleBaseException(BaseException ex) {
        log.warn("BaseException [{}]: {}", ex.getStatus(), ex.getMessage());
        var error = new ApiErrors(
                ex.getStatus().value(),
                ex.getMessage(),
                new Date()
        );
        return ResponseEntity.status(ex.getStatus()).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrors> handleGenericException(Exception ex) {
        log.error("Unhandled exception", ex);
        var error = new ApiErrors(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro interno do servidor",
                new Date()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}