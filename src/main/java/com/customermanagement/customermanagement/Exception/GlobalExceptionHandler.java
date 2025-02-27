package com.customermanagement.customermanagement.Exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MyException.class)
    public ResponseEntity<CustomerNotFoundException> exceptionHandler(MyException e){


        CustomerNotFoundException customException = new CustomerNotFoundException();

        customException.setStatus(HttpStatus.NOT_FOUND);
        customException.setError(e.getMessage());


        logger.info("Exception created Successfully!!");

        return new ResponseEntity<>(customException,HttpStatus.NOT_FOUND);


    }
}
