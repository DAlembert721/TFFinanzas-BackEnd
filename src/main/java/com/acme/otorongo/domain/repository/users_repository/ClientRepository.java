package com.acme.otorongo.domain.repository.users_repository;

import com.acme.otorongo.domain.model.users.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    void deleteById(Long id);
}
