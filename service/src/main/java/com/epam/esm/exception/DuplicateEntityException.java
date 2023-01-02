package com.epam.esm.exception;


public class DuplicateEntityException extends Exception{

    public DuplicateEntityException() {
    }

    public DuplicateEntityException(String message) {
        super(message);
    }
}
