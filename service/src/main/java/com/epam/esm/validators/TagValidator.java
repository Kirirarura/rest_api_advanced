package com.epam.esm.validators;

import com.epam.esm.entity.Tag;
import com.epam.esm.exception.InvalidEntityException;

/**
 * Class validator that checks provided Tag.
 */
public class TagValidator {

    private TagValidator() {
    }

    private static final int NAME_MAX_LENGTH = 20;
    private static final int NAME_MIN_LENGTH = 2;

    public static void isValid(Tag tag) throws InvalidEntityException {
        isNameValid(tag.getName());
    }


    /**
     * Method for checking tag name.
     *
     * @param name Name of provided tag.
     * @throws InvalidEntityException An exception that thrown in case provided name size not fitting in range.
     */
    private static void isNameValid(String name) throws InvalidEntityException {
        if (name == null){
            throw new InvalidEntityException("40006");
        }
        if (!(name.length() <= NAME_MAX_LENGTH && name.length() >= NAME_MIN_LENGTH)){
            throw new InvalidEntityException("40007");
        }

    }
}
