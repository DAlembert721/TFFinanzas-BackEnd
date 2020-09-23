package com.acme.otorongo.domain.repository;

import com.acme.otorongo.domain.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByIdAndUserId(Long id, Long userId);
}
