package com.epam.esm.exception;

public class NoSuchEntityException extends Exception{
    public NoSuchEntityException() {
    }

    public NoSuchEntityException(String message) {
        super(message);
    }
}
