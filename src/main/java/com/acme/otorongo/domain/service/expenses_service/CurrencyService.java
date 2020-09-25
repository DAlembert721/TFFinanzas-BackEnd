package com.acme.otorongo.domain.service.expenses_service;

import com.acme.otorongo.domain.model.expenses.Currency;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CurrencyService {
    List<Currency> getAllCurrencies();
    Currency createCurrency(Currency currency);
    Currency getCurrencyById(Long currencyId);
    Currency updateCurrency(Long currencyId, Currency currency);
    ResponseEntity<?> deleteCurrency(Long currencyId);
}
