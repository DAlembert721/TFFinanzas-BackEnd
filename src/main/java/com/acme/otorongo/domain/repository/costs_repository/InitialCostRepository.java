package com.acme.otorongo.domain.repository.costs_repository;

import com.acme.otorongo.domain.model.costs.InitialCost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InitialCostRepository extends JpaRepository<InitialCost, Long> {
    void deleteById(Long id);
}
