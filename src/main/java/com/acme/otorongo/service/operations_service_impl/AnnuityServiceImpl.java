package com.acme.otorongo.service.operations_service_impl;

import com.acme.otorongo.domain.model.operations.Annuity;
import com.acme.otorongo.domain.repository.operations_repository.AnnuityRepository;
import com.acme.otorongo.domain.service.operations_service.AnnuityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnuityServiceImpl implements AnnuityService {
    private final AnnuityRepository annuityRepository;

    @Autowired
    public AnnuityServiceImpl(AnnuityRepository annuityRepository){
        this.annuityRepository = annuityRepository;
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
    public Annuity save(Annuity annuity) {
        return null;
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
