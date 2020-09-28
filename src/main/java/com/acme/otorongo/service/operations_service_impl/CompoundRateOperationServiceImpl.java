package com.acme.otorongo.service.operations_service_impl;

import com.acme.otorongo.domain.model.operations.CompoundRateOperation;
import com.acme.otorongo.domain.repository.expenses_repository.*;
import com.acme.otorongo.domain.repository.operations_repository.CompoundRateOperationRepository;
import com.acme.otorongo.domain.repository.users_repository.ClientRepository;
import com.acme.otorongo.domain.service.operations_service.CompoundRateOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompoundRateOperationServiceImpl implements CompoundRateOperationService {

    private final CompoundRateOperationRepository compoundRateOperationRepository;
    private final ClientRepository clientRepository;
    private final CurrencyRepository currencyRepository;
    private final QuotationRepository quotationRepository;
    private final RateRepository rateRepository;

    @Autowired
    public CompoundRateOperationServiceImpl(CompoundRateOperationRepository compoundRateOperationRepository,
                                            ClientRepository clientRepository, CurrencyRepository currencyRepository,
                                            QuotationRepository quotationRepository, RateRepository rateRepository){
        this.compoundRateOperationRepository = compoundRateOperationRepository;
        this.clientRepository = clientRepository;
        this.currencyRepository = currencyRepository;
        this.quotationRepository = quotationRepository;
        this.rateRepository = rateRepository;
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
    public CompoundRateOperation save(CompoundRateOperation compoundRateOperation, Long clientId,
                                      Long currencyId, Long quotationId, Long rateId) {
        compoundRateOperation.setClient(clientRepository.findById(clientId).orElse(null));
        compoundRateOperation.setCurrency(currencyRepository.findById(currencyId).orElse(null));
        compoundRateOperation.setQuotation(quotationRepository.findById(quotationId).orElse(null));
        compoundRateOperation.setRate(rateRepository.findById(rateId).orElse(null));
        return compoundRateOperationRepository.save(compoundRateOperation);
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
