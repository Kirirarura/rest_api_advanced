package com.epam.esm.dao;

import com.epam.esm.entity.Tag;
import com.epam.esm.exceptions.DaoException;

import java.util.List;
import java.util.Optional;

/**
 * An interface that represents relation with database, mainly working with Tags table.
 */
public interface TagDao {

    /**
     * Method to save tag entity to database.
     *
     * @param tag Entity object to save
     * @throws DaoException An exception that thrown in case of data access errors.
     */
    void create(Tag tag) throws DaoException;

    /**
     * Method to get all tags entity from database.
     *
     * @return List of Tags.
     * @throws DaoException An exception that thrown in case of data access errors.
     */
    List<Tag> getAll() throws DaoException;

    /**
     * Method to get tag entity from database by providing an id of specific tag entity.
     *
     * @param id Provided ID of Tag.
     * @return Optional of Tag entity.
     * @throws DaoException An exception that thrown in case of data access errors.
     */
    Optional<Tag> getById(Long id) throws DaoException;

    /**
     * Method to get tag entity from database by providing name of specific tag entity.
     *
     * @param name String name.
     * @return Optional of Tag entity.
     * @throws DaoException An exception that thrown in case of data access errors.
     */
    Optional<Tag> findByName(String name) throws DaoException;

    /**
     * Method to delete an entity from database by provided id of specific tag entity.
     *
     * @param id Provided ID of Tag.
     * @return An id of deleted Tag.
     * @throws DaoException An exception that thrown in case of data access errors.
     */
    Long deleteById(Long id) throws DaoException;
}
