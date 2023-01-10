package com.epam.esm.service;

import com.epam.esm.entity.Tag;
import com.epam.esm.exception.DuplicateEntityException;
import com.epam.esm.exception.InvalidEntityException;
import com.epam.esm.exception.InvalidIdException;
import com.epam.esm.exception.NoSuchEntityException;
import com.epam.esm.exceptions.DaoException;

import java.util.List;

/**
 * Business logic for Tags
 */
public interface TagsService {

    /**
     * Method for creating tag.
     *
     * @param tag Tag object to be created.
     * @return ID of created tag.
     * @throws DaoException             An exception that thrown in case of data access errors.
     * @throws InvalidEntityException   An exception that thrown in case provided gift certificate is invalid.
     * @throws DuplicateEntityException An exception that thrown in case gift certificate is already presented in DB.
     */
    Long create(Tag tag) throws DuplicateEntityException, InvalidEntityException, DaoException;

    /**
     * Method for getting all tags.
     *
     * @return List of tags
     * @throws DaoException An exception that thrown in case of data access errors.
     */
    List<Tag> getAll() throws DaoException;

    /**
     * Method for getting tag by id.
     *
     * @param id ID of tag to be returned.
     * @return Tag object.
     * @throws NoSuchEntityException An exception that thrown in case gift certificate with provided id not found.
     * @throws DaoException          An exception that thrown in case of data access errors.
     * @throws InvalidIdException    An exception that thrown in case provided ID is invalid.
     */
    Tag getById(Long id) throws NoSuchEntityException, DaoException, InvalidIdException;

    /**
     * Method for deleting tag.
     *
     * @param id ID of tag to be deleted.
     * @return ID of deleted tag.
     * @throws NoSuchEntityException An exception that thrown in case gift certificate with provided id not found.
     * @throws DaoException          An exception that thrown in case of data access errors.
     * @throws InvalidIdException    An exception that thrown in case provided ID is invalid.
     */
    Long deleteById(Long id) throws NoSuchEntityException, DaoException, InvalidIdException;
}
