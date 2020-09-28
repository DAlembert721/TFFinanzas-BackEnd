package com.acme.otorongo.domain.service.operations_service;

import com.acme.otorongo.domain.model.operations.Operation;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OperationService {
    List<Operation> getAllOperations();
    List<Operation> getAllOperationsByCurrencyId(Long currencyId);
    List<Operation> getAllOperationsByClientId(Long clientId);
    Operation getOperationId(Long operationId);
    ResponseEntity<?> deleteOperation(Long operationId);
}
