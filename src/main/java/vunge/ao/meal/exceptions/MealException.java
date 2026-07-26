package vunge.ao.meal.exceptions;

import org.springframework.http.HttpStatus;

public class MealException extends BaseException {
    public MealException(String message, HttpStatus status){
        super(message, status);
    }
}
