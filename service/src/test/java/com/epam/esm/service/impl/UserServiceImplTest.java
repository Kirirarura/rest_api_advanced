package com.epam.esm.service.impl;

import com.epam.esm.dao.UserDao;
import com.epam.esm.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    UserDao userDao;

    @InjectMocks
    UserServiceImpl userService;

    private User getUser(){
        return new User(1L, "FirstName", "LastName");
    }

    private List<User> getUsers() {
        return Arrays.asList(
                getUser(),
                new User(2L, "FirstName", "LastName")
        );
    }

    private static final int PAGE = 0;
    private static final int SIZE = 5;

    @Test
    void getById() {
        User user = getUser();
        when(userDao.findById(user.getId())).thenReturn(Optional.of(user));

        User actual = userService.getById(user.getId());
        assertEquals(user, actual);
    }

    @Test
    void getAll() {
        List<User> users = getUsers();
        Pageable pageRequest = PageRequest.of(PAGE, SIZE);
        when(userDao.findAll(pageRequest)).thenReturn(users);

        List<User> actual = userService.getAll(PAGE, SIZE);
        assertEquals(users, actual);
    }
}