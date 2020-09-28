package com.acme.otorongo.service.operations_service_impl;

import com.acme.otorongo.domain.model.operations.Annuity;
import com.acme.otorongo.domain.repository.expenses_repository.CurrencyRepository;
import com.acme.otorongo.domain.repository.expenses_repository.QuotationRepository;
import com.acme.otorongo.domain.repository.expenses_repository.RateRepository;
import com.acme.otorongo.domain.repository.operations_repository.AnnuityRepository;
import com.acme.otorongo.domain.repository.users_repository.ClientRepository;
import com.acme.otorongo.domain.service.operations_service.AnnuityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnuityServiceImpl implements AnnuityService {
    private final AnnuityRepository annuityRepository;
    private final ClientRepository clientRepository;
    private final CurrencyRepository currencyRepository;
    private final QuotationRepository quotationRepository;
    private final RateRepository rateRepository;

    @Autowired
    public AnnuityServiceImpl(AnnuityRepository annuityRepository,
                              ClientRepository clientRepository, CurrencyRepository currencyRepository,
                              QuotationRepository quotationRepository, RateRepository rateRepository){
        this.annuityRepository = annuityRepository;
        this.clientRepository = clientRepository;
        this.currencyRepository = currencyRepository;
        this.quotationRepository = quotationRepository;
        this.rateRepository = rateRepository;
    }

    @Override
    public List<Annuity> getAllAnnuities() {
        return annuityRepository.findAll();
    }

    @Override
    public List<Annuity> getAllAnnuitiesByRateId(Long rateId) {
        return annuityRepository.findByRateId(rateId);
    }

    @Override
    public List<Annuity> getAllAnnuitiesByQuotationId(Long quotationId) {
        return annuityRepository.findByQuotationId(quotationId);
    }

    @Override
    public Annuity save(Annuity annuity, Long clientId, Long currencyId, Long quotationId, Long rateId) {
        annuity.setClient(clientRepository.findById(clientId).orElse(null));
        annuity.setCurrency(currencyRepository.findById(currencyId).orElse(null));
        annuity.setQuotation(quotationRepository.findById(quotationId).orElse(null));
        annuity.setRate(rateRepository.findById(rateId).orElse(null));
        return annuityRepository.save(annuity);
    }

    @Override
    public Annuity update(Long annuityId, Annuity annuity) {
        Annuity existed = annuityRepository.findById(annuityId).orElse(null);
        if (existed == null)
            return null;
        existed.setInitialQuota(annuity.getInitialQuota());
        existed.setCapital(annuity.getCapital());
        existed.setFuture(annuity.getFuture());
        existed.setDiscountDate(annuity.getDiscountDate());
        existed.setExpireDate(annuity.getExpireDate());
        existed.setRateValue(annuity.getRateValue());
        existed.setState(annuity.getState());
        return annuityRepository.save(existed);
    }
}
