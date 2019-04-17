package ru.itis.web.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(value = IncorrectDataException.class)
    public ResponseEntity<JsonResponse> handleIncorrectDataException(IncorrectDataException e) {
        JsonResponse response = JsonResponse.builder()
                .field(e.getFieldName())
                .message(e.getMessage())
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
