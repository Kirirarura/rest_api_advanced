package com.epam.esm.service.impl;

import com.epam.esm.dao.UserDao;
import com.epam.esm.entity.User;
import com.epam.esm.exception.ExceptionMessageKey;
import com.epam.esm.exception.NoSuchEntityException;
import com.epam.esm.service.UserService;
import com.epam.esm.service.util.PaginationHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    private static Supplier<NoSuchEntityException> getNoSuchUserException(
            Long id) {
        return () -> new NoSuchEntityException(ExceptionMessageKey.NO_ENTITY, id);
    }

    @Override
    public User getById(Long id) {
        return userDao.findById(id).orElseThrow(getNoSuchUserException(id));
    }

    @Override
    public List<User> getAll(int page, int size) {
        Pageable pageRequest = PaginationHelper.createPageRequest(page, size);
        return userDao.findAll(pageRequest);
    }
}
