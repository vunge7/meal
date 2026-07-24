package vunge.ao.meal.exceptions;

import org.springframework.http.HttpStatus;

public class CategoryException extends BaseException {
    public CategoryException(String message, HttpStatus status){
        super(message, status);
    }
}
