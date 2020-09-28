package com.acme.otorongo.domain.repository.operations_repository;

import com.acme.otorongo.domain.model.operations.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {
}
