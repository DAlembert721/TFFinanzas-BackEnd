package com.acme.otorongo.controller.operations_controller;


import com.acme.otorongo.domain.model.operations.CompoundRateOperation;
import com.acme.otorongo.domain.service.operations_service.CompoundRateOperationService;
import com.acme.otorongo.resource.operations_resource.CompoundRateOperationResource;
import com.acme.otorongo.resource.save_operations_resource.SaveCompoundRateOperationResource;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CompoundRateOperationController {

    private final CompoundRateOperationService compoundRateOperationService;
    private final ModelMapper mapper;

    @Autowired
    public CompoundRateOperationController(CompoundRateOperationService compoundRateOperationService, ModelMapper mapper){
        this.compoundRateOperationService = compoundRateOperationService;
        this.mapper = mapper;
    }

    @GetMapping("/compoundRateOperations")
    public List<CompoundRateOperationResource> getAllCompoundRateOperations(){
        List<CompoundRateOperation> compoundRateOperations = compoundRateOperationService.getAllCompoundRateOperations();
        return compoundRateOperations.stream().map(this::convertToResource).collect(Collectors.toList());
    }

    @GetMapping("/rates/{rateId}/compoundRateOperations")
    public List<CompoundRateOperationResource> getAllCompoundRateOperationsByRateId(
            @PathVariable(name = "rateId") Long rateId){
        List<CompoundRateOperation> compoundRateOperations = compoundRateOperationService
                .getAllCompoundRateOperationsByRateId(rateId);
        return compoundRateOperations.stream().map(this::convertToResource).collect(Collectors.toList());
    }

    @GetMapping("/quotation/{quotationId}/compoundRateOperations")
    public List<CompoundRateOperationResource> getAllCompoundRateOperationsByQuotationId(
            @PathVariable(name = "quotationId") Long quotationId){
        List<CompoundRateOperation> compoundRateOperations = compoundRateOperationService
                .getAllCompoundRateOperationsByQuotationId(quotationId);
        return compoundRateOperations.stream().map(this::convertToResource).collect(Collectors.toList());
    }

    @PostMapping("/clients/{clientId}/currencies/{currencyId}/quotations/{quotationId}/rates/{rateId}/compoundRateOperations")
    public CompoundRateOperationResource createAnnuity(@RequestBody SaveCompoundRateOperationResource compoundRateOperation,
                                         @PathVariable(name = "clientId") Long clientId,
                                         @PathVariable(name = "currencyId") Long currencyId,
                                         @PathVariable(name = "quotationId") Long quotationId,
                                         @PathVariable(name = "rateId") Long rateId){
        CompoundRateOperation newCompoundRateOperation = compoundRateOperationService
                .save(convertToEntity(compoundRateOperation), clientId, currencyId, quotationId, rateId);
        return convertToResource(newCompoundRateOperation);
    }

    @PutMapping("/compoundRateOperations/{compoundRateOperationId}")
    public CompoundRateOperationResource updateCompoundRateOperation(
            @PathVariable(name = "compoundRateOperationId") Long compoundRateOperationId,
            @RequestBody SaveCompoundRateOperationResource compoundRateOperation){
        CompoundRateOperation updated = compoundRateOperationService
                .update(compoundRateOperationId, convertToEntity(compoundRateOperation));
        return convertToResource(updated);
    }

    @PostConstruct
    public void init(){
        mapper.addMappings(new PropertyMap<CompoundRateOperation, CompoundRateOperationResource>() {
            @Override
            protected void configure() {
                map().setQuotationName(source.getQuotation().getName());
                map().setRateName(source.getRate().getName());
            }
        });
    }

    private CompoundRateOperation convertToEntity(SaveCompoundRateOperationResource resource){
        return mapper.map(resource, CompoundRateOperation.class);
    }

    private CompoundRateOperationResource convertToResource(CompoundRateOperation entity){
        return mapper.map(entity, CompoundRateOperationResource.class);
    }


}
