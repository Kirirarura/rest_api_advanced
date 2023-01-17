package com.epam.esm.dao;

import com.epam.esm.dao.query.QueryBuilder;
import com.epam.esm.exceptions.DaoException;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.epam.esm.exceptions.DaoExceptionCodes.*;

/**
 * Class designed to implement basic operations with database.
 */
public abstract class AbstractDao<T> {

    protected final String findByIdQuery;
    protected final String findByColumnQuery;
    protected final String deleteByIdQuery;
    protected final String getAllQuery;
    private final QueryBuilder queryBuilder;
    private final RowMapper<T> rowMapper;
    private final JdbcTemplate jdbcTemplate;
    private static final String SELECT_ALL_FROM = "SELECT * FROM ";

    protected AbstractDao(RowMapper<T> rowMapper, String tableName, QueryBuilder queryBuilder, JdbcTemplate jdbcTemplate) {
        this.rowMapper = rowMapper;
        this.queryBuilder = queryBuilder;
        this.jdbcTemplate = jdbcTemplate;

        getAllQuery = SELECT_ALL_FROM + tableName;
        findByIdQuery = SELECT_ALL_FROM + tableName + " WHERE id=?";
        findByColumnQuery = SELECT_ALL_FROM + tableName + " WHERE %s=?";
        deleteByIdQuery = "DELETE FROM " + tableName + " WHERE id=?";
    }
    protected abstract String getTableName();

    public List<T> getAll() throws DaoException {
        try {
            return jdbcTemplate.query(getAllQuery, rowMapper);
        } catch (DataAccessException e) {
            throw new DaoException(NO_ENTITY);
        }
    }

    public Optional<T> getById(Long id) throws DaoException {
        try {
            return jdbcTemplate.query(findByIdQuery, rowMapper, id)
                    .stream().findAny();
        } catch (DataAccessException e) {
            throw new DaoException(NO_ENTITY_WITH_ID);
        }
    }

    public Long deleteById(Long id) throws DaoException {
        try {
            jdbcTemplate.update(deleteByIdQuery, id);
        } catch (DataAccessException e) {
            throw new DaoException(NO_ENTITY_WITH_ID);
        }
        return id;
    }

    public Optional<T> findByColumn(String columnName, String value) throws DaoException {
        try {
            String query = String.format(findByColumnQuery, columnName);
            return jdbcTemplate.query(query, rowMapper, value).stream().findAny();
        } catch (DataAccessException e){
            throw new DaoException(NO_ENTITY_WITH_NAME);
        }

    }

    /**
     * Method for executing a query in the database to update objects.
     *
     * @param query  query to execute
     * @param params parameters involved in the request
     */
    protected void executeUpdateQuery(String query, Object... params) {
        jdbcTemplate.update(query, params);
    }

    /**
     * Method to get gift certificate entities according to specific parameters and filters.
     *
     * @param fields Map with filters.
     * @return List of gift certificates.
     * @throws DaoException An exception that thrown in case of data access errors.
     */
    public List<T> getWithFilters(Map<String, String> fields) throws DaoException {
        try {
            String query = queryBuilder.createGetQuery(fields, getTableName());
            return jdbcTemplate.query(query, rowMapper);
        } catch (DataAccessException e) {
            throw new DaoException(NO_ENTITY_WITH_PARAMETERS);
        }
    }
}
