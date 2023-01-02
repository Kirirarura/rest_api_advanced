package com.epam.esm.service;

import com.epam.esm.dao.TagDao;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.DuplicateEntityException;
import com.epam.esm.exception.InvalidEntityException;
import com.epam.esm.exception.NoSuchEntityException;
import com.epam.esm.exceptions.DaoException;
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

    private void checkTag(Tag tag) throws InvalidEntityException, DuplicateEntityException {
        if (!TagValidator.isValid(tag)){
            throw new InvalidEntityException("tag.invalid");
        }
        String name = tag.getName();
        Optional<Tag> optionalName = tagDao.findByName(name);
        if (optionalName.isPresent()){
            throw new DuplicateEntityException("tag.already.exist");
        }
    }

    @Override
    public List<Tag> getAll() {
        return tagDao.getAll();
    }

    @Override
    public Tag getById(long id) throws NoSuchEntityException {
        Optional<Tag> optionalTag = tagDao.findById(id);
        if (!optionalTag.isPresent()) {
            throw new NoSuchEntityException("tag.not.found");
        }
        return optionalTag.get();
    }

    @Override
    public void deleteById(long id) throws NoSuchEntityException {
        Optional<Tag> optionalTag = tagDao.findById(id);
        if (!optionalTag.isPresent()) {
            throw new NoSuchEntityException("tag.not.found");
        }
        tagDao.deleteById(id);
    }
}
