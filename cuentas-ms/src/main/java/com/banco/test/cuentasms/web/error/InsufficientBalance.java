package com.banco.test.cuentasms.web.error;

public class InsufficientBalance extends RuntimeException{

    public InsufficientBalance(String message){
        super(message);
    }
}
