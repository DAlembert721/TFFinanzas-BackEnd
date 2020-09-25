package com.acme.otorongo.domain.service.users_service;

import com.acme.otorongo.domain.model.users.User;
import org.springframework.http.ResponseEntity;

public interface UserService {
    User createUser(User user);
    User getUserById(Long userId);
    User updateUser(Long userId, User userRequest);
    ResponseEntity<?> deleteUser(Long userId);
}
