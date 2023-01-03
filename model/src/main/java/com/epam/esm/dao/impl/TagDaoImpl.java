package com.epam.esm.dao.impl;

import com.epam.esm.dao.AbstractDao;
import com.epam.esm.dao.TagDao;
import com.epam.esm.dao.query.Queries;
import com.epam.esm.entity.Tag;
import com.epam.esm.exceptions.DaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.epam.esm.exceptions.ExceptionDaoMessages.SAVING_ERROR;


@Repository
public class TagDaoImpl extends AbstractDao<Tag> implements TagDao {

    private static final Logger log = LoggerFactory.getLogger(TagDaoImpl.class);

    private static final String TABLE_NAME = "tags";
    private static final RowMapper<Tag> ROW_MAPPER = new BeanPropertyRowMapper<>(Tag.class);
    private final JdbcTemplate jdbcTemplate;

    public TagDaoImpl(JdbcTemplate jdbcTemplate) {
        super(ROW_MAPPER, TABLE_NAME, jdbcTemplate);
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Tag tag) throws DaoException {
        try {
            jdbcTemplate.update(Queries.CREATE_TAG, tag.getName());
        } catch (DataAccessException e) {
            log.error("Failed to create Tag, cause: {}", e.getMessage());
            throw new DaoException(SAVING_ERROR);
        }
    }

    @Override
    public Optional<Tag> findByName(String name) throws DaoException {
        return findByColumn("name", name);
    }
}
