package com.acme.otorongo.domain.service.operations_service;

import com.acme.otorongo.domain.model.operations.CompoundRateOperation;

import java.util.List;

public interface CompoundRateOperationService {
    List<CompoundRateOperation> getAllCompoundRateOperations();
    List<CompoundRateOperation> getAllCompoundRateOperationsByRateId(Long rateId);
    List<CompoundRateOperation> getAllCompoundRateOperationsByQuotationId(Long quotationId);
    CompoundRateOperation save(CompoundRateOperation compoundRateOperation);
    CompoundRateOperation update(Long compoundRateOperationId, CompoundRateOperation compoundRateOperation);
}
