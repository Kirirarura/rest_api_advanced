package com.epam.esm.controllers;

import com.epam.esm.dto.UserDto;
import com.epam.esm.entity.User;
import com.epam.esm.hateoas.UserHateoas;
import com.epam.esm.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    private final UserService userService;
    private final UserHateoas userHateoas;

    @GetMapping("/{id}")
    public UserDto userById(@PathVariable Long id) {
        return userHateoas.toModel(userService.getById(id));
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CollectionModel<UserDto> allUsers(@RequestParam(value = "page", defaultValue = "0", required = false) int page,
                                             @RequestParam(value = "size", defaultValue = "5", required = false) int size) {
        List<User> users = userService.getAll(page, size);
        return userHateoas.toCollectionModel(users);
    }

}
