package com.acme.otorongo.service.expenses_service_impl;

import com.acme.otorongo.domain.model.expenses.Rate;
import com.acme.otorongo.domain.repository.expenses_repository.RateRepository;
import com.acme.otorongo.domain.service.expenses_service.RateService;
import com.acme.otorongo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RateServiceImpl implements RateService {

    private final RateRepository rateRepository;

    @Autowired
    public RateServiceImpl(RateRepository rateRepository){
        this.rateRepository = rateRepository;
    }

    @Override
    public List<Rate> getAllRates() {
        return rateRepository.findAll();
    }

    @Override
    public Rate createRate(Rate rate) {
        return rateRepository.save(rate);
    }

    @Override
    public Rate getRateById(Long rateId) {
        return rateRepository.findById(rateId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Rate", "Id", rateId));
    }

    @Override
    public Rate updateRate(Long rateId, Rate rate) {
        Rate existed = rateRepository.findById(rateId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Rate", "Id", rateId));
        existed.setName(rate.getName());
        existed.setTime(rate.getTime());
        return rateRepository.save(existed);
    }

    @Override
    public ResponseEntity<?> deleteRate(Long rateId) {
        rateRepository.deleteById(rateId);
        return ResponseEntity.ok().build();
    }
}
