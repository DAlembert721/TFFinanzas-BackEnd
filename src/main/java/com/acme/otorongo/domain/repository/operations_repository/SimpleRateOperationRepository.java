package com.acme.otorongo.domain.repository.operations_repository;

import com.acme.otorongo.domain.model.operations.SimpleRateOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SimpleRateOperationRepository extends JpaRepository<SimpleRateOperation, Long> {
}
