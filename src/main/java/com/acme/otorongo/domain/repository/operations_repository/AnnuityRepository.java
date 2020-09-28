package com.acme.otorongo.domain.repository.operations_repository;

import com.acme.otorongo.domain.model.operations.Annuity;
import com.acme.otorongo.domain.model.operations.SimpleRateOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnnuityRepository extends JpaRepository<Annuity, Long> {
    List<Annuity> findByQuotationId(Long quotationId);
    List<Annuity> findByRateId(Long rateId);
}
