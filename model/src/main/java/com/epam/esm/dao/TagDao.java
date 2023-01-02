package com.epam.esm.dao;

import com.epam.esm.entity.Tag;
import com.epam.esm.exceptions.DaoException;

import java.util.List;
import java.util.Optional;

public interface TagDao {

    void create(Tag tag) throws DaoException;

    List<Tag> getAll();

    Optional<Tag> findById(Long id);

    Optional<Tag> findByName(String name);

    void deleteById(Long id);
}
