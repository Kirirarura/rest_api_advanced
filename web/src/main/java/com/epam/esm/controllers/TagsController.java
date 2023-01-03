package com.epam.esm.controllers;

import com.epam.esm.entity.Tag;
import com.epam.esm.exception.DuplicateEntityException;
import com.epam.esm.exception.InvalidEntityException;
import com.epam.esm.exception.NoSuchEntityException;
import com.epam.esm.exceptions.DaoException;
import com.epam.esm.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagsController {

    private final TagsService tagsService;

    @Autowired
    public TagsController(TagsService tagsService) {
        this.tagsService = tagsService;
    }

    @GetMapping
    public List<Tag> getAll() throws DaoException {
        return tagsService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Long> create(@RequestBody Tag tag) throws DaoException, InvalidEntityException, DuplicateEntityException {
        Long id = tagsService.create(tag);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Tag getById(@PathVariable("id") Long id) throws NoSuchEntityException, DaoException {
        return tagsService.getById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id) throws NoSuchEntityException, DaoException {
        tagsService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(id);
    }
}
