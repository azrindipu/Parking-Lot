package com.autoParkingLot.autoParkingLot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.text.SimpleDateFormat;
import java.util.Date;

@ControllerAdvice
public class ExceptionHandeler extends ResponseEntityExceptionHandler {
    private String datePattern = "yyyy-MM-dd HH:mm";
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datePattern);

    @ExceptionHandler(ApiExceptionBadRequest.class)
    public final ResponseEntity<Object> badRequest(ApiExceptionBadRequest apiException){
        return new ResponseEntity(this.create(apiException), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ApiExceptionInternalServerErrorException.class)
    public final ResponseEntity<Object> internalServerError(ApiExceptionInternalServerErrorException apiException){
        return new ResponseEntity(this.create(apiException), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ApiExceptionNotFoundException.class)
    public final ResponseEntity<Object> notFound(ApiExceptionNotFoundException apiException){
        return new ResponseEntity(this.create(apiException), HttpStatus.NOT_FOUND);
    }

    private ExceptionResponse create(RuntimeException ex){
        return new ExceptionResponse(simpleDateFormat.format(new Date().getTime()),
                ex.getMessage());
    }
}
