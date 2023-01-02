package com.epam.esm.validators;

import com.epam.esm.entity.GiftCertificate;

import java.math.BigDecimal;

public class GiftCertificateValidator {

    private static final int NAME_MAX_LENGTH = 80;
    private static final int NAME_MIN_LENGTH = 1;
    private static final BigDecimal PRICE_MIN_VALUE = BigDecimal.ONE;
    private static final BigDecimal PRICE_MAX_VALUE = new BigDecimal(Integer.MAX_VALUE);
    private static final int DURATION_MIN_VALUE = 1;

    private GiftCertificateValidator() {
    }

    public static boolean isValid(GiftCertificate giftCertificate){
        return isNameValid(giftCertificate.getName()) &&
                isPriceValid(giftCertificate.getPrice()) &&
                isDurationValid(giftCertificate.getDuration());
    }

    private static boolean isNameValid(String name) {
        if (name == null) {
            return false;
        }
        return name.length() >= NAME_MIN_LENGTH && name.length() <= NAME_MAX_LENGTH;
    }

    private static boolean isPriceValid(BigDecimal price) {
        if (price == null) {
            return false;
        }
        return price.compareTo(PRICE_MIN_VALUE) >= 0 &&
                price.compareTo(PRICE_MAX_VALUE) <= 0;
    }

    private static boolean isDurationValid(int duration) {
        return duration >= DURATION_MIN_VALUE;
    }
}
