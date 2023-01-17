package com.epam.esm.validators;

import com.epam.esm.exception.InvalidIdException;

/**
 * Class validator that checks provided id.
 *
 */
public class IdValidator {

    private IdValidator() {
    }

    /**
     * Method for checking provided ID.
     *
     * @param id Provided ID.
     * @throws InvalidIdException An exception that thrown in case provided ID is less than 1.
     */
    public static void checkForInvalidId(Long id) throws InvalidIdException {
        if (id < 1){
            throw new InvalidIdException("40005", id);
        }
    }
}
