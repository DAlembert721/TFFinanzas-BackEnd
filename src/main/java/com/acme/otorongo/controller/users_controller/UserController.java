package com.acme.otorongo.controller.users_controller;

import com.acme.otorongo.domain.model.users.User;
import com.acme.otorongo.domain.service.users_service.UserService;
import com.acme.otorongo.resource.save_users_resource.SaveUserResource;
import com.acme.otorongo.resource.users_resource.UserResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserController {

    private final ModelMapper mapper;
    private final UserService userService;

    @Autowired
    public UserController(UserService userService, ModelMapper mapper){
        this.userService = userService;
        this.mapper = mapper;
    }

    @PostMapping("/users")
    public UserResource createUser(@Valid @RequestBody SaveUserResource userRequest) {
        User user = convertToEntity(userRequest);
        return convertToResource(userService.createUser(user));
    }

    @GetMapping("/users/{userId}")
    public UserResource getUserById(@PathVariable Long userId) {
        return convertToResource(userService.getUserById(userId));
    }

    private User convertToEntity(SaveUserResource resource) {
        return mapper.map(resource, User.class);
    }

    private UserResource convertToResource(User entity) {
        return mapper.map(entity, UserResource.class);
    }
}
