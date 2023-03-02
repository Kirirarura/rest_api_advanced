package com.epam.esm.dao;

import com.epam.esm.entity.User;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    Optional<User> findById(Long id);

    List<User> findAll(Pageable pageable);
}
