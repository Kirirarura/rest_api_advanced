package com.epam.esm.service;

import com.epam.esm.dao.TagDao;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.DuplicateEntityException;
import com.epam.esm.exception.InvalidEntityException;
import com.epam.esm.exception.InvalidIdException;
import com.epam.esm.exception.NoSuchEntityException;
import com.epam.esm.exceptions.DaoException;
import com.epam.esm.validators.IdValidator;
import com.epam.esm.validators.TagValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TagsServiceImpl implements TagsService{

    private final TagDao tagDao;

    @Autowired
    public TagsServiceImpl(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    @Override
    @Transactional
    public Long create(Tag tag) throws DuplicateEntityException, InvalidEntityException, DaoException {
        checkTag(tag);
        String tagName = tag.getName();
        tagDao.create(tag);

        return tagDao.findByName(tagName)
                .map(Tag::getId).orElse(-1L);
    }

    private void checkTag(Tag tag) throws InvalidEntityException, DuplicateEntityException, DaoException {
        TagValidator.isValid(tag);
        String name = tag.getName();
        Optional<Tag> optionalName = tagDao.findByName(name);
        if (optionalName.isPresent()){
            throw new DuplicateEntityException("40902");
        }
    }

    @Override
    public List<Tag> getAll() throws DaoException {
        return tagDao.getAll();
    }

    @Override
    public Tag getById(Long id) throws NoSuchEntityException, DaoException, InvalidIdException {
        IdValidator.checkForInvalidId(id);
        Optional<Tag> optionalTag = tagDao.getById(id);
        if (!optionalTag.isPresent()) {
            throw new NoSuchEntityException("40402");
        }
        return optionalTag.get();
    }

    @Override
    public Long deleteById(Long id) throws NoSuchEntityException, DaoException, InvalidIdException {
        IdValidator.checkForInvalidId(id);
        Optional<Tag> optionalTag = tagDao.getById(id);
        if (!optionalTag.isPresent()) {
            throw new NoSuchEntityException("40402");
        }
        tagDao.deleteById(id);
        return id;
    }
}
