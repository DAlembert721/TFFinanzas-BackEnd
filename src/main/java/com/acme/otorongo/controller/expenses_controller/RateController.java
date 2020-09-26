package com.acme.otorongo.controller.expenses_controller;

import com.acme.otorongo.domain.model.expenses.Rate;
import com.acme.otorongo.domain.service.expenses_service.RateService;
import com.acme.otorongo.resource.expenses_resource.RateResource;
import com.acme.otorongo.resource.save_expenses_resource.SaveRateResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class RateController {

    private final RateService rateService;
    private final ModelMapper mapper;

    @Autowired
    public RateController(RateService rateService, ModelMapper mapper){
        this.rateService = rateService;
        this.mapper = mapper;
    }

    @GetMapping("/rates")
    public List<RateResource> getAllRates(){
        List<Rate> rates = rateService.getAllRates();
        return rates.stream().map(this::convertToResource).collect(Collectors.toList());
    }

    @GetMapping("/rates/{rateId}")
    public RateResource getRateById(@PathVariable(name = "rateId") Long rateId){
        return convertToResource(rateService.getRateById(rateId));
    }

    @PostMapping("/rates")
    public RateResource createRate(@RequestBody SaveRateResource rate){
        Rate newRate = rateService.createRate(convertToEntity(rate));
        return convertToResource(newRate);
    }

    @PutMapping("/rates/{rateId}")
    public RateResource updateRate(@RequestBody SaveRateResource rate,
                                   @PathVariable(name = "rateId") Long rateId){
        Rate updated = rateService.updateRate(rateId, convertToEntity(rate));
        return convertToResource(updated);
    }

    @DeleteMapping("/rates/{rateId}")
    public void deleteRate(@PathVariable(name = "rateId") Long rateId){
        rateService.deleteRate(rateId);
    }

    private Rate convertToEntity(SaveRateResource resource){
        return mapper.map(resource, Rate.class);
    }

    private RateResource convertToResource(Rate entity){
        return mapper.map(entity, RateResource.class);
    }
}
