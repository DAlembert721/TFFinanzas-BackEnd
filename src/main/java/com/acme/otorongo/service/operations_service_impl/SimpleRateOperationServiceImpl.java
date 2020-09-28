package com.acme.otorongo.service.operations_service_impl;

import com.acme.otorongo.domain.model.operations.SimpleRateOperation;
import com.acme.otorongo.domain.repository.operations_repository.SimpleRateOperationRepository;
import com.acme.otorongo.domain.service.operations_service.SimpleRateOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimpleRateOperationServiceImpl implements SimpleRateOperationService {

    private final SimpleRateOperationRepository simpleRateOperationRepository;

    @Autowired
    public SimpleRateOperationServiceImpl(SimpleRateOperationRepository simpleRateOperationRepository){
        this.simpleRateOperationRepository = simpleRateOperationRepository;
    }

    @Override
    public List<SimpleRateOperation> getAllSimpleRateOperations() {
        return simpleRateOperationRepository.findAll();
    }

    @Override
    public SimpleRateOperation save(SimpleRateOperation simpleRateOperation) {
        return null;
    }

    @Override
    public SimpleRateOperation update(Long simpleRateOperationId, SimpleRateOperation simpleRateOperation) {
        SimpleRateOperation existed = simpleRateOperationRepository.findById(simpleRateOperationId).orElse(null);
        if (existed == null)
            return null;
        existed.setCapital(simpleRateOperation.getCapital());
        existed.setFuture(simpleRateOperation.getFuture());
        existed.setDiscountDate(simpleRateOperation.getDiscountDate());
        existed.setExpireDate(simpleRateOperation.getExpireDate());
        existed.setRateValue(simpleRateOperation.getRateValue());
        existed.setState(simpleRateOperation.getState());
        return simpleRateOperationRepository.save(existed);
    }
}
