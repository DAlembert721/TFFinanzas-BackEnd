package com.acme.otorongo.controller.expenses_controller;

import com.acme.otorongo.domain.model.expenses.Currency;
import com.acme.otorongo.domain.service.expenses_service.CurrencyService;
import com.acme.otorongo.resource.expenses_resource.CurrencyResource;
import com.acme.otorongo.resource.save_expenses_resource.SaveCurrencyResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CurrencyController {

    private final CurrencyService currencyService;
    private final ModelMapper mapper;

    @Autowired
    public CurrencyController(CurrencyService currencyService, ModelMapper mapper){
        this.currencyService = currencyService;
        this.mapper = mapper;
    }

    @GetMapping("/currencies")
    public List<CurrencyResource> getAllCurrencies(){
        List<Currency> currencies = currencyService.getAllCurrencies();
        return currencies.stream().map(this::convertToResource).collect(Collectors.toList());
    }

    @GetMapping("/currencies/{currencyId}")
    public CurrencyResource getCurrencyById(@PathVariable(name = "currencyId") Long currencyId){
        return convertToResource(currencyService.getCurrencyById(currencyId));
    }

    @PostMapping("/currencies")
    public CurrencyResource createCurrency(@RequestBody SaveCurrencyResource currency){
        Currency newCurrency = currencyService.createCurrency(convertToEntity(currency));
        return convertToResource(newCurrency);
    }

    @PutMapping("/currencies/{currencyId}")
    public CurrencyResource updateCurrency(@RequestBody SaveCurrencyResource currency,
                                           @PathVariable(name = "currencyId") Long currencyId){
        Currency updated = currencyService.updateCurrency(currencyId, convertToEntity(currency));
        return convertToResource(updated);
    }

    @DeleteMapping("/currencies/{currencyId}")
    public ResponseEntity<?> deleteCurrency(@PathVariable(name = "currencyId") Long currencyId){
        return currencyService.deleteCurrency(currencyId);
    }

    private Currency convertToEntity(SaveCurrencyResource resource){
        return mapper.map(resource, Currency.class);
    }

    private CurrencyResource convertToResource(Currency entity){
        return mapper.map(entity, CurrencyResource.class);
    }
}
