package com.epam.esm.validators;

import com.epam.esm.exception.InvalidIdException;

public class IdValidator {

    public static void checkForInvalidId(Long id) throws InvalidIdException {
        if (id < 1){
            throw new InvalidIdException("40005", id);
        }
    }
}
