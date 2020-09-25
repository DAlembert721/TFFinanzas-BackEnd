package com.acme.otorongo.domain.service.expenses_service;

import com.acme.otorongo.domain.model.expenses.Rate;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RateService {
    List<Rate> getAllRates();
    Rate createRate(Rate rate);
    Rate getRateById(Long rateId);
    Rate updateRate(Long rateId, Rate rate);
    ResponseEntity<?> deleteRate(Long rateId);
}
