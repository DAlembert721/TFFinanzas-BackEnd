package com.acme.otorongo.domain.repository.expenses_repository;

import com.acme.otorongo.domain.model.expenses.Quotation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuotationRepository extends JpaRepository<Quotation, Long> {
}
