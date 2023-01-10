package com.epam.esm.dao;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.exceptions.DaoException;

import java.util.List;
import java.util.Map;
import java.util.Optional;


/**
 * An interface that represents relation with database, mainly working with Gift Certificates table.
 */
public interface GiftCertificateDao {

    /**
     * Method to save tag entity to database.
     *
     * @param giftCertificate Gift Certificate entity to be saved.
     * @param tags            Tags that going to reference to gift certificate.
     * @throws DaoException An exception that thrown in case of data access errors.
     */
    void create(GiftCertificate giftCertificate, List<Tag> tags) throws DaoException;

    /**
     * Method to get all gift certificates entity from database.
     *
     * @return List of gift certificates.
     * @throws DaoException An exception that thrown in case of data access errors.
     */
    List<GiftCertificate> getAll() throws DaoException;

    /**
     * Method to get gift certificate entity from database by providing an id of specific gift certificate entity.
     *
     * @param id Provided ID of gift certificate.
     * @return Optional of gift certificate entity.
     * @throws DaoException An exception that thrown in case of data access errors.
     */
    Optional<GiftCertificate> getById(Long id) throws DaoException;

    /**
     * Method to get gift certificate entity from database by providing name of specific gift certificate entity.
     *
     * @param name String name.
     * @return Optional of gift certificate entity.
     * @throws DaoException An exception that thrown in case of data access errors.
     */
    Optional<GiftCertificate> findByName(String name) throws DaoException;

    /**
     * Method to delete gift certificate entity from database by provided id of specific gift certificate entity.
     *
     * @param id Provided ID of gift certificate.
     * @return An id of deleted gift certificate.
     * @throws DaoException An exception that thrown in case of data access errors.
     */
    Long deleteById(Long id) throws DaoException;

    /**
     * Method to update gift certificate entity in database.
     *
     * @param giftCertificate Gift certificate entity with parameters to update.
     * @param certificateTags List of tags to update
     * @throws DaoException An exception that thrown in case of data access errors.
     */
    void update(GiftCertificate giftCertificate, List<Tag> certificateTags) throws DaoException;

    /**
     * Method to get gift certificate entities according to specific parameters and filters.
     *
     * @param fields Map with filters.
     * @return List of gift certificates.
     * @throws DaoException An exception that thrown in case of data access errors.
     */
    List<GiftCertificate> getWithFilters(Map<String, String> fields) throws DaoException;
}
