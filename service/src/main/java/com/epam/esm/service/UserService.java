package com.epam.esm.service;

import com.epam.esm.entity.User;

import java.util.List;

public interface UserService {

    User getById(Long id);

    List<User> getAll(int page, int size);

}
