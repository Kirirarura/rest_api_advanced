package com.epam.esm.dao.impl;

import com.epam.esm.dao.AbstractDao;
import com.epam.esm.dao.TagDao;
import com.epam.esm.dao.query.QueryBuilder;
import com.epam.esm.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

import static com.epam.esm.dao.query.Queries.*;

@Repository
@Transactional
public class TagDaoImpl extends AbstractDao<Tag> implements TagDao {

    private final QueryBuilder<Tag> queryBuilder;

    @Autowired
    public TagDaoImpl(QueryBuilder<Tag> queryBuilder) {
        super(Tag.class);
        this.queryBuilder = queryBuilder;
    }

    @Override
    protected QueryBuilder<Tag> getQueryCreator() {
        return queryBuilder;
    }

    @Override
    public Optional<Tag> findMostPopularTagOfUserWithHighestCostOfAllOrders() {
        return entityManager.createQuery(FIND_MOST_WIDELY_USED_TAG_OF_USER_WITH_HIGHEST_COST_OF_ALL_ORDERS_QUERY, entityType)
                .setMaxResults(1)
                .getResultList().stream()
                .findFirst();
    }
}
