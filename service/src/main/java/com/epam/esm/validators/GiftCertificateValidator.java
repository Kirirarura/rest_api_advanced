package com.epam.esm.validators;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.exception.InvalidEntityException;

import java.math.BigDecimal;

/**
 * Class validator that checks provided gift certificate.
 */
public class GiftCertificateValidator {

    private static final int NAME_MAX_LENGTH = 80;
    private static final int NAME_MIN_LENGTH = 1;
    private static final BigDecimal PRICE_MIN_VALUE = BigDecimal.ONE;
    private static final BigDecimal PRICE_MAX_VALUE = new BigDecimal(Integer.MAX_VALUE);
    private static final int DURATION_MIN_VALUE = 1;

    private GiftCertificateValidator() {
    }

    /**
     * Method for validating gift certificate.
     *
     * @param giftCertificate Provided gift certificate object.
     * @throws InvalidEntityException An exception that thrown in case provided gift certificate is invalid.
     */
    public static void isValid(GiftCertificate giftCertificate) throws InvalidEntityException {
        isNameValid(giftCertificate.getName());
        isPriceValid(giftCertificate.getPrice());
        isDurationValid(giftCertificate.getDuration());
    }

    /**
     * Method for validating gift certificate for update.
     *
     * @param giftCertificate Provided gift certificate object for update.
     * @throws InvalidEntityException An exception that thrown in case provided gift certificate is invalid.
     */
    public static void isValidForUpdate(GiftCertificate giftCertificate) throws InvalidEntityException {
        if (giftCertificate.getName() != null) {
            isNameValid(giftCertificate.getName());
        }
        if (giftCertificate.getPrice() != null) {
            isPriceValid(giftCertificate.getPrice());
        }
        if (giftCertificate.getDuration() != 0) {
            isDurationValid(giftCertificate.getDuration());
        }
    }


    /**
     * Method for checking gift certificate name.
     *
     * @param name Provided gift certificate name.
     * @throws InvalidEntityException An exception that thrown in case provided gift certificate name is invalid.
     */
    private static void isNameValid(String name) throws InvalidEntityException {
        if (name == null) {
            throw new InvalidEntityException("40001");
        }
        if (!(name.length() >= NAME_MIN_LENGTH && name.length() <= NAME_MAX_LENGTH)) {
            throw new InvalidEntityException("40002");
        }
    }

    /**
     * Method for checking gift certificate price.
     *
     * @param price Provided gift certificate price.
     * @throws InvalidEntityException An exception that thrown in case provided gift certificate price is invalid.
     */
    private static void isPriceValid(BigDecimal price) throws InvalidEntityException {
        if (price == null) {
            throw new InvalidEntityException("40001");
        }
        if (!(price.compareTo(PRICE_MIN_VALUE) >= 0 && price.compareTo(PRICE_MAX_VALUE) <= 0)) {
            throw new InvalidEntityException("40003");
        }
    }

    /**
     * Method for checking gift certificate duration.
     *
     * @param duration Provided gift certificate duration.
     * @throws InvalidEntityException An exception that thrown in case provided gift certificate duration is invalid.
     */
    private static void isDurationValid(int duration) throws InvalidEntityException {
        if ((duration < DURATION_MIN_VALUE)) {
            throw new InvalidEntityException("40004");
        }
    }
}
