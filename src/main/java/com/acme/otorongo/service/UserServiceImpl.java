package com.acme.otorongo.service;

import com.acme.otorongo.domain.model.User;
import com.acme.otorongo.domain.repository.UserRepository;
import com.acme.otorongo.domain.service.UserService;
import com.acme.otorongo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User", "Id", userId));
    }

    @Override
    public User updateUser(Long userId, User userRequest) {
        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User", "Id", userId));
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        return userRepository.save(user);
    }

    @Override
    public ResponseEntity<?> deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User", "id", userId));
        userRepository.delete(user);
        return ResponseEntity.ok().build();
    }
}
