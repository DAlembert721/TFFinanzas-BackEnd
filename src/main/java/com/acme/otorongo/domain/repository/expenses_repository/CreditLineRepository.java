package com.acme.otorongo.domain.repository.expenses_repository;

import com.acme.otorongo.domain.model.expenses.CreditLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditLineRepository extends JpaRepository<CreditLine, Long> {
    List<CreditLine> findByClientId(Long clientId);
    void deleteById(Long id);
}
