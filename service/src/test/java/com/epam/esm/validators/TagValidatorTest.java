package com.epam.esm.validators;

import com.epam.esm.entity.Tag;
import com.epam.esm.exception.InvalidEntityException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TagValidatorTest {

    private static final Tag VALID_TAG = new Tag(1L, "tag1");
    private static final Tag INVALID_NAME_TAG = new Tag(1L, "");
    private static final Tag NULL_NAME_TAG = new Tag(1L, null);

    //    MethodName_StateUnderTest_ExpectedBehavior

    @Test
    void isValid_ValidTag_ExceptionDoesNotThrow() {
        assertDoesNotThrow(() -> TagValidator.isValid(VALID_TAG));
    }

    @Test
    void isValid_InvalidNameTag_ExceptionThrow() {
        assertThrows(InvalidEntityException.class,
                () -> TagValidator.isValid(INVALID_NAME_TAG));
    }

    @Test
    void isValid_NullNameTag_ExceptionThrow() {
        assertThrows(InvalidEntityException.class,
                () -> TagValidator.isValid(NULL_NAME_TAG));
    }
}