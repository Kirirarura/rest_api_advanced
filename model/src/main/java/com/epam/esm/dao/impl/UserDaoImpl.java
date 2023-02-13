package com.epam.esm.dao.impl;

import com.epam.esm.dao.AbstractDao;
import com.epam.esm.dao.UserDao;
import com.epam.esm.dao.query.QueryBuilder;
import com.epam.esm.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    public UserDaoImpl() {
        super(User.class);
    }

    @Override
    protected QueryBuilder<User> getQueryCreator() {
        return null;
    }

}
