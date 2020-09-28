package com.acme.otorongo.service.operations_service_impl;

import com.acme.otorongo.domain.model.operations.SimpleRateOperation;
import com.acme.otorongo.domain.repository.expenses_repository.CurrencyRepository;
import com.acme.otorongo.domain.repository.operations_repository.SimpleRateOperationRepository;
import com.acme.otorongo.domain.repository.users_repository.ClientRepository;
import com.acme.otorongo.domain.service.operations_service.SimpleRateOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimpleRateOperationServiceImpl implements SimpleRateOperationService {

    private final SimpleRateOperationRepository simpleRateOperationRepository;
    private final ClientRepository clientRepository;
    private final CurrencyRepository currencyRepository;

    @Autowired
    public SimpleRateOperationServiceImpl(SimpleRateOperationRepository simpleRateOperationRepository,
                                          ClientRepository clientRepository, CurrencyRepository currencyRepository){
        this.simpleRateOperationRepository = simpleRateOperationRepository;
        this.clientRepository = clientRepository;
        this.currencyRepository = currencyRepository;
    }

    @Override
    public List<SimpleRateOperation> getAllSimpleRateOperations() {
        return simpleRateOperationRepository.findAll();
    }

    @Override
    public SimpleRateOperation save(SimpleRateOperation simpleRateOperation, Long clientId, Long currencyId) {
        simpleRateOperation.setClient(clientRepository.findById(clientId).orElse(null));
        simpleRateOperation.setCurrency(currencyRepository.findById(currencyId).orElse(null));
        return simpleRateOperationRepository.save(simpleRateOperation);
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
