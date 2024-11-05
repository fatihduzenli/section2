package com.eazybank.loans.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = org.springframework.http.HttpStatus.BAD_REQUEST)
public class LoanAlreadyExistsException extends RuntimeException{
    public LoanAlreadyExistsException(String message){
        super(message);
    }
}
