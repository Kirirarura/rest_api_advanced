package com.epam.esm.validators;

import com.epam.esm.exception.InvalidIdException;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertThrows;


class IdValidatorTest {

    @Test
    void testIdValidatorThrowException() {
        assertThrows(InvalidIdException.class, () -> IdValidator.checkForInvalidId(-1L));
    }

}
