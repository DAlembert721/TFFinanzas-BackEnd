package com.acme.otorongo.domain.repository;

import com.acme.otorongo.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
