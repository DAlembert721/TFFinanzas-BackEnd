package com.acme.otorongo.service.expenses_service_impl;

import com.acme.otorongo.domain.model.expenses.Currency;
import com.acme.otorongo.domain.repository.expenses_repository.CurrencyRepository;
import com.acme.otorongo.domain.service.expenses_service.CurrencyService;
import com.acme.otorongo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRepository currencyRepository;

    @Autowired
    public CurrencyServiceImpl(CurrencyRepository currencyRepository){
        this.currencyRepository = currencyRepository;
    }

    @Override
    public List<Currency> getAllCurrencies() {
        return currencyRepository.findAll();
    }

    @Override
    public Currency createCurrency(Currency currency) {
        return currencyRepository.save(currency);
    }

    @Override
    public Currency getCurrencyById(Long currencyId) {
        return currencyRepository.findById(currencyId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Currency", "Id", currencyId));
    }

    @Override
    public Currency updateCurrency(Long currencyId, Currency currency) {
        Currency existed = currencyRepository.findById(currencyId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Currency", "Id", currencyId));
        existed.setName(currency.getName());
        existed.setDenomination(currency.getDenomination());
        return currencyRepository.save(existed);
    }

    @Override
    public ResponseEntity<?> deleteCurrency(Long currencyId) {
        currencyRepository.deleteById(currencyId);
        return ResponseEntity.ok().build();
    }
}
