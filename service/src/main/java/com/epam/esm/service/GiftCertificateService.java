package com.epam.esm.service;


import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.exception.DuplicateEntityException;
import com.epam.esm.exception.InvalidEntityException;
import com.epam.esm.exception.InvalidIdException;
import com.epam.esm.exception.NoSuchEntityException;
import com.epam.esm.exceptions.DaoException;

import java.util.List;
import java.util.Map;

/**
 * Business logic for Gift Certificates
 */
public interface GiftCertificateService {

    /**
     * Method for creation of gift certificates.
     * Validates provided gift certificate object and sets dates.
     * Saves new tags if such provided.
     *
     * @param giftCertificateDto DTO of gift certificate to be created.
     * @return ID of created gift certificate.
     * @throws DaoException             An exception that thrown in case of data access errors.
     * @throws InvalidEntityException   An exception that thrown in case provided gift certificate is invalid.
     * @throws DuplicateEntityException An exception that thrown in case gift certificate is already presented in DB.
     */
    Long create(GiftCertificateDto giftCertificateDto) throws DaoException, InvalidEntityException, DuplicateEntityException;

    /**
     * Method for getting all gift certificates.
     *
     * @return List of gift certificates.
     * @throws DaoException An exception that thrown in case of data access errors.
     */
    List<GiftCertificate> getAll() throws DaoException;

    /**
     * Method for getting gift certificate by id.
     *
     * @param id ID of gift certificate to be returned.
     * @return Gift certificate object.
     * @throws NoSuchEntityException An exception that thrown in case gift certificate with provided id not found.
     * @throws DaoException          An exception that thrown in case of data access errors.
     * @throws InvalidIdException    An exception that thrown in case provided ID is invalid.
     */
    GiftCertificate getById(Long id) throws NoSuchEntityException, DaoException, InvalidIdException;

    /**
     * Method for deleting gift certificate.
     *
     * @param id ID of gift certificate to be deleted.
     * @return ID of deleted gift certificate.
     * @throws NoSuchEntityException An exception that thrown in case gift certificate with provided ID not found.
     * @throws DaoException          An exception that thrown in case of data access errors.
     * @throws InvalidIdException    An exception that thrown in case provided ID is invalid.
     */
    Long deleteById(Long id) throws NoSuchEntityException, DaoException, InvalidIdException;

    /**
     * Method for gift certificate updating.
     *
     * @param id                 ID of gift certificate to be updated.
     * @param giftCertificateDto DTO for gift certificate update.
     * @return ID of updated gift certificate.
     * @throws DaoException           An exception that thrown in case of data access errors.
     * @throws InvalidEntityException An exception that thrown in case provided gift certificate is invalid.
     * @throws InvalidIdException     An exception that thrown in case provided ID is invalid.
     */
    Long update(Long id, GiftCertificateDto giftCertificateDto) throws DaoException, InvalidEntityException, InvalidIdException;

    /**
     * Method for getting gift certificates according to search filters.
     *
     * @param map Mapped filters.
     * @return List of gift certificates got with filters.
     * @throws DaoException An exception that thrown in case of data access errors.
     */
    List<GiftCertificate> doFilter(Map<String, String> map) throws DaoException;
}
