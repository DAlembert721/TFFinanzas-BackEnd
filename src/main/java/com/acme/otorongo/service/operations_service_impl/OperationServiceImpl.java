package com.acme.otorongo.service.operations_service_impl;

import com.acme.otorongo.domain.model.operations.Operation;
import com.acme.otorongo.domain.repository.expenses_repository.CurrencyRepository;
import com.acme.otorongo.domain.repository.operations_repository.OperationRepository;
import com.acme.otorongo.domain.repository.users_repository.ClientRepository;
import com.acme.otorongo.domain.service.operations_service.OperationService;
import com.acme.otorongo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperationServiceImpl implements OperationService {
    private final OperationRepository operationRepository;

    @Autowired
    public OperationServiceImpl(OperationRepository operationRepository){
        this.operationRepository = operationRepository;
    }

    @Override
    public List<Operation> getAllOperations() {
        return operationRepository.findAll();
    }

    @Override
    public List<Operation> getAllOperationsByCurrencyId(Long currencyId) {
        return operationRepository.findByCurrencyId(currencyId);
    }

    @Override
    public List<Operation> getAllOperationsByClientId(Long clientId) {
        return operationRepository.findByClientId(clientId);
    }

    @Override
    public Operation getOperationId(Long operationId) {
        return operationRepository.findById(operationId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Operation", "Id", operationId));
    }

    @Override
    public ResponseEntity<?> deleteOperation(Long operationId) {
        operationRepository.deleteById(operationId);
        return ResponseEntity.ok().build();
    }
}
