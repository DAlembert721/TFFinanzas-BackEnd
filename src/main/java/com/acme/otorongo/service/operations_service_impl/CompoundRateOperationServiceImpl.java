package com.acme.otorongo.service.operations_service_impl;

import com.acme.otorongo.domain.model.operations.CompoundRateOperation;
import com.acme.otorongo.domain.model.operations.SimpleRateOperation;
import com.acme.otorongo.domain.repository.operations_repository.CompoundRateOperationRepository;
import com.acme.otorongo.domain.service.operations_service.CompoundRateOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompoundRateOperationServiceImpl implements CompoundRateOperationService {

    private final CompoundRateOperationRepository compoundRateOperationRepository;

    @Autowired
    public CompoundRateOperationServiceImpl(CompoundRateOperationRepository compoundRateOperationRepository){
        this.compoundRateOperationRepository = compoundRateOperationRepository;
    }

    @Override
    public List<CompoundRateOperation> getAllCompoundRateOperations() {
        return compoundRateOperationRepository.findAll();
    }

    @Override
    public List<CompoundRateOperation> getAllCompoundRateOperationsByRateId(Long rateId) {
        return compoundRateOperationRepository.findByRateId(rateId);
    }

    @Override
    public List<CompoundRateOperation> getAllCompoundRateOperationsByQuotationId(Long quotationId) {
        return compoundRateOperationRepository.findByQuotationId(quotationId);
    }

    @Override
    public CompoundRateOperation save(CompoundRateOperation compoundRateOperation) {
        return null;
    }

    @Override
    public CompoundRateOperation update(Long compoundRateOperationId, CompoundRateOperation compoundRateOperation) {
        CompoundRateOperation existed = compoundRateOperationRepository.findById(compoundRateOperationId).orElse(null);
        if (existed == null)
            return null;
        existed.setCapital(compoundRateOperation.getCapital());
        existed.setFuture(compoundRateOperation.getFuture());
        existed.setDiscountDate(compoundRateOperation.getDiscountDate());
        existed.setExpireDate(compoundRateOperation.getExpireDate());
        existed.setRateValue(compoundRateOperation.getRateValue());
        existed.setState(compoundRateOperation.getState());
        return compoundRateOperationRepository.save(existed);
    }
}
