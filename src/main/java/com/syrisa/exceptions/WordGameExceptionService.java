package com.syrisa.exceptions;

public abstract class WordGameExceptionService extends Exception{
    protected WordGameExceptionService(String message) {
        super(message);
    }
}