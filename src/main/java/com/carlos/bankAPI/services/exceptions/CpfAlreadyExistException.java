package com.carlos.bankAPI.services.exceptions;

public class CpfAlreadyExistException extends  RuntimeException{

    public  CpfAlreadyExistException (Object cpf){
        super("CPF Already Exist. cpf" + cpf);
    }

}
