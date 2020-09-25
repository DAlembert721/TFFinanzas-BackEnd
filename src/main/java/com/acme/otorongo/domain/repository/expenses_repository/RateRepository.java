package com.acme.otorongo.domain.repository.expenses_repository;

import com.acme.otorongo.domain.model.expenses.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RateRepository extends JpaRepository<Rate, Long> {
}
