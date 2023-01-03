package com.epam.esm.dao;

import com.epam.esm.entity.Tag;
import com.epam.esm.exceptions.DaoException;

import java.util.List;
import java.util.Optional;

public interface TagDao {

    void create(Tag tag) throws DaoException;

    List<Tag> getAll() throws DaoException;

    Optional<Tag> findById(Long id) throws DaoException;

    Optional<Tag> findByName(String name) throws DaoException;

    void deleteById(Long id) throws DaoException;
}
