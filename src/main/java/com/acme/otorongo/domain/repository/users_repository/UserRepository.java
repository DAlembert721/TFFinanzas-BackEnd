package com.acme.otorongo.domain.repository.users_repository;

import com.acme.otorongo.domain.model.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
