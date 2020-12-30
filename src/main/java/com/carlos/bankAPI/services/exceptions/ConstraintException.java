package com.carlos.bankAPI.services.exceptions;

public class ConstraintException extends  RuntimeException{

    public ConstraintException(String msg){
        super(msg);
    }

}
