package com.customermanagement.customermanagement.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;


public class CustomerNotFoundException {

    private HttpStatus status;

    private String error;

//    public CustomerNotFoundException(HttpStatus status, String error) {
//        this.status = status;
//        this.error = error;
//    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
