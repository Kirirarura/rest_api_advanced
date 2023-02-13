package com.epam.esm.validation;

import com.epam.esm.dao.query.SortType;
import com.epam.esm.exception.ExceptionResult;
import lombok.experimental.UtilityClass;

import java.util.Objects;

import static com.epam.esm.exception.ExceptionMessageKey.*;


@UtilityClass
public class FilterParamsValidator {

    private final int MAX_LENGTH_NAME = 80;
    private final int MIN_LENGTH_NAME = 4;

    public void validateGiftCertificateName(String name, ExceptionResult er) {
        if (name == null || name.length() < MIN_LENGTH_NAME || name.length() > MAX_LENGTH_NAME) {
            er.addException(BAD_GIFT_CERTIFICATE_NAME, name);
        }
    }

    public void validateTagName(String name, ExceptionResult er) {
        if (name == null || name.length() < MIN_LENGTH_NAME || name.length() > MAX_LENGTH_NAME) {
            er.addException(BAD_TAG_NAME, name);
        }
    }

    public void validateSortType(String sortType, ExceptionResult er) {
        if (!(Objects.equals(SortType.ASC.getSortTypeName(), sortType) ||
                Objects.equals(SortType.DESC.getSortTypeName(), sortType))) {
            er.addException(BAD_SORT_TYPE, sortType);
        }
    }
}
