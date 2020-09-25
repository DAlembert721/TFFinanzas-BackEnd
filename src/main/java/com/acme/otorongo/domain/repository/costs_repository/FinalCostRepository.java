package com.acme.otorongo.domain.repository.costs_repository;

import com.acme.otorongo.domain.model.costs.FinalCost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinalCostRepository extends JpaRepository<FinalCost, Long> {
    void deleteById(Long id);
}
