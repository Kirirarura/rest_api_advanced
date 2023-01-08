package com.epam.esm.validators;

import com.epam.esm.entity.Tag;
import com.epam.esm.exception.InvalidEntityException;

public class TagValidator {

    private TagValidator() {
    }

    private static final int NAME_MAX_LENGTH = 20;
    private static final int NAME_MIN_LENGTH = 2;

    public static void isValid(Tag tag) throws InvalidEntityException {
        isNameValid(tag.getName());
    }


    private static void isNameValid(String name) throws InvalidEntityException {
        if (name == null){
            throw new InvalidEntityException("40006");
        }
        if (!(name.length() <= NAME_MAX_LENGTH && name.length() >= NAME_MIN_LENGTH)){
            throw new InvalidEntityException("40007");
        }

    }
}
