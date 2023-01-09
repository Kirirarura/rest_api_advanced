package com.epam.esm.controllers;

import com.epam.esm.entity.Tag;
import com.epam.esm.exception.DuplicateEntityException;
import com.epam.esm.exception.InvalidEntityException;
import com.epam.esm.exception.InvalidIdException;
import com.epam.esm.exception.NoSuchEntityException;
import com.epam.esm.exceptions.DaoException;
import com.epam.esm.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller responsible for all operations with tags.
 */
@RestController
@RequestMapping("/tags")
public class TagsController {

    private final TagsService tagsService;

    @Autowired
    public TagsController(TagsService tagsService) {
        this.tagsService = tagsService;
    }

    /**
     * Method for getting all tags.
     *
     * @return List of tags
     * @throws DaoException An exception that thrown in case of data access errors.
     */
    @GetMapping
    public List<Tag> getAll() throws DaoException {
        return tagsService.getAll();
    }

    /**
     * Method for getting tag by id.
     *
     * @param id ID of tag to be returned.
     * @return Tag object.
     * @throws NoSuchEntityException An exception that thrown in case gift certificate with provided id not found.
     * @throws DaoException An exception that thrown in case of data access errors.
     * @throws InvalidIdException An exception that thrown in case provided ID is invalid.
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Tag getById(@PathVariable("id") Long id) throws NoSuchEntityException, DaoException, InvalidIdException {
        return tagsService.getById(id);
    }

    /**
     * @param tag Tag object to be created.
     * @return ID of created tag.
     * @throws DaoException An exception that thrown in case of data access errors.
     * @throws InvalidEntityException An exception that thrown in case provided gift certificate is invalid.
     * @throws DuplicateEntityException An exception that thrown in case gift certificate is already presented in DB.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Long> create(@RequestBody Tag tag) throws DaoException, InvalidEntityException, DuplicateEntityException {
        Long id = tagsService.create(tag);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    /**
     * Method for deleting tag.
     * @param id ID of tag to be deleted.
     * @return ID of deleted tag.
     * @throws NoSuchEntityException An exception that thrown in case gift certificate with provided id not found.
     * @throws DaoException An exception that thrown in case of data access errors.
     * @throws InvalidIdException An exception that thrown in case provided ID is invalid.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id) throws NoSuchEntityException, DaoException, InvalidIdException {
        tagsService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(id);
    }
}
