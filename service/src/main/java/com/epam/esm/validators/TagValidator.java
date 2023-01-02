package com.epam.esm.validators;

import com.epam.esm.entity.Tag;

public class TagValidator {

    private TagValidator() {
    }

    private static final int NAME_MAX_LENGTH = 20;
    private static final int NAME_MIN_LENGTH = 1;

    public static boolean isValid(Tag tag){
        return isNameValid(tag.getName());
    }

    private static boolean isNameValid(String name){
        if (name == null){
            return false;
        }
        return name.length() <= NAME_MAX_LENGTH && name.length() >= NAME_MIN_LENGTH;
    }
}
