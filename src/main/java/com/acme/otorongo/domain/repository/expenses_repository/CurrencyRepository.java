package com.acme.otorongo.domain.repository.expenses_repository;

import com.acme.otorongo.domain.model.expenses.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {
}
