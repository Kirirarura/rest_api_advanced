package com.epam.esm.validators;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.exception.InvalidEntityException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GiftCertificateValidatorTest {

    private static final GiftCertificate VALID_GIFT_CERTIFICATE = new GiftCertificate(
            1L, "giftCertificate1",
            "description1", new BigDecimal("10.00"), 1, "2020-08-29T06:12:15.156",
            "2020-08-29T06:12:15.156"
    );

    private static final GiftCertificate INVALID_NAME_GIFT_CERTIFICATE = new GiftCertificate(
            1L, "",
            "description1", new BigDecimal("10.00"), 10, "2020-08-29T06:12:15.156",
            "2020-08-29T06:12:15.156"
    );

    private static final GiftCertificate INVALID_PRICE_GIFT_CERTIFICATE = new GiftCertificate(
            1L, "description1",
            "description1", new BigDecimal("-10.00"), 10, "2020-08-29T06:12:15.156",
            "2020-08-29T06:12:15.156"
    );

    private static final GiftCertificate NULL_PRICE_GIFT_CERTIFICATE = new GiftCertificate(
            1L, "description1",
            "description1", null, 10, "2020-08-29T06:12:15.156",
            "2020-08-29T06:12:15.156"
    );

    private static final GiftCertificate INVALID_DURATION_GIFT_CERTIFICATE = new GiftCertificate(
            1L, "description1",
            "description1", new BigDecimal("10.00"), -10, "2020-08-29T06:12:15.156",
            "2020-08-29T06:12:15.156"
    );


    @Test
    void isValid_ValidGiftCertificate_ExceptionDoesNotThrow() {
        assertDoesNotThrow(() -> GiftCertificateValidator.isValid(VALID_GIFT_CERTIFICATE));
    }

    @Test
    void isValid_InvalidNameGiftCertificate_ExceptionThrow() {
        assertThrows(InvalidEntityException.class,
                () -> GiftCertificateValidator.isValid(INVALID_NAME_GIFT_CERTIFICATE));
    }

    @Test
    void isValid_NullNameGiftCertificate_ExceptionThrow() {
        INVALID_NAME_GIFT_CERTIFICATE.setName(null);
        assertThrows(InvalidEntityException.class,
                () -> GiftCertificateValidator.isValid(INVALID_NAME_GIFT_CERTIFICATE));
    }

    @Test
    void isValid_InvalidPriceGiftCertificate_ExceptionThrow() {
        assertThrows(InvalidEntityException.class,
                () -> GiftCertificateValidator.isValid(INVALID_PRICE_GIFT_CERTIFICATE));
    }

    @Test
    void isValid_NullPriceGiftCertificate_ExceptionThrow() {
        assertThrows(InvalidEntityException.class,
                () -> GiftCertificateValidator.isValid(NULL_PRICE_GIFT_CERTIFICATE));
    }

    @Test
    void isValid_InvalidDurationGiftCertificate_ExceptionThrow() {
        assertThrows(InvalidEntityException.class,
                () -> GiftCertificateValidator.isValid(INVALID_DURATION_GIFT_CERTIFICATE));
    }

    @Test
    void isValidForUpdate_ValidGiftCertificate_ExceptionDoesNotThrow() {
        assertDoesNotThrow(() -> GiftCertificateValidator.isValidForUpdate(VALID_GIFT_CERTIFICATE));
    }

    @Test
    void isValidForUpdate_InvalidGiftCertificate_ExceptionThrow() {
        assertThrows(InvalidEntityException.class,
                () -> GiftCertificateValidator.isValidForUpdate(INVALID_NAME_GIFT_CERTIFICATE));
    }
}