package com.desafio.pagamentos.exceptions;

public class StatusInvalidoException extends RuntimeException{
    public  StatusInvalidoException(String msg){
        super(msg);
    }
}
