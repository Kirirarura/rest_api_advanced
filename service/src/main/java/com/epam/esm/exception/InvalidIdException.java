package com.epam.esm.exception;

public class InvalidIdException extends Exception{

    private Long id;
    public InvalidIdException(String message) {
        super(message);
    }
    public InvalidIdException(String message, Long id){
        super(message);
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
