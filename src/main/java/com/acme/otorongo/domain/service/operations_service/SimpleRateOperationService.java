package com.acme.otorongo.domain.service.operations_service;

import com.acme.otorongo.domain.model.operations.SimpleRateOperation;

import java.util.List;

public interface SimpleRateOperationService {
    List<SimpleRateOperation> getAllSimpleRateOperations();
    SimpleRateOperation save(SimpleRateOperation simpleRateOperation);
    SimpleRateOperation update(Long simpleRateOperationId, SimpleRateOperation simpleRateOperation);
}
