package com.acme.otorongo.domain.repository.users_repository;

import com.acme.otorongo.domain.model.users.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findByUserId(Long userId);
    List<Client> findByDistrictId(Long districtId);
    void deleteById(Long id);
}
