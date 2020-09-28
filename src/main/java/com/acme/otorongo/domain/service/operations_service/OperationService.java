package com.acme.otorongo.domain.service.operations_service;

import com.acme.otorongo.domain.model.operations.Operation;
import com.acme.otorongo.domain.model.promissories.PromissoryNote;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OperationService {
    List<Operation> getAllOperations();
    List<Operation> getAllOperationsByCurrencyId(Long currencyId);
    List<Operation> getAllOperationsByClientId(Long clientId);
    Operation createOperation(Operation operation, Long currencyId, Long clientId);
    Operation getOperationId(Long operationId);
    Operation updateOperation(Long operationId, Operation operation);
    ResponseEntity<?> deleteOperation(Long operationId);
}
