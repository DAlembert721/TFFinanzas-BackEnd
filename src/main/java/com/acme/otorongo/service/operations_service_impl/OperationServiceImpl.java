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
    private final ClientRepository clientRepository;
    private final CurrencyRepository currencyRepository;

    @Autowired
    public OperationServiceImpl(OperationRepository operationRepository, ClientRepository clientRepository,
                                CurrencyRepository currencyRepository){
        this.operationRepository = operationRepository;
        this.clientRepository = clientRepository;
        this.currencyRepository = currencyRepository;
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
    public Operation createOperation(Operation operation, Long currencyId, Long clientId) {
        return null;
    }

    @Override
    public Operation getOperationId(Long operationId) {
        return operationRepository.findById(operationId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Operation", "Id", operationId));
    }

    @Override
    public Operation updateOperation(Long operationId, Operation operation) {
        Operation existed = operationRepository.findById(operationId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Operation", "Id", operationId));
        existed.setDiscountDate(operation.getDiscountDate());
        existed.setCurrency(operation.getCurrency());
        existed.setExpireDate(operation.getExpireDate());
        existed.setRateValue(operation.getRateValue());
        existed.setState(operation.getState());
        return operationRepository.save(existed);
    }

    @Override
    public ResponseEntity<?> deleteOperation(Long operationId) {
        operationRepository.deleteById(operationId);
        return ResponseEntity.ok().build();
    }
}
