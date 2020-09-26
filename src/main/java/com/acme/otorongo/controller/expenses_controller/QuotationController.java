package com.acme.otorongo.controller.expenses_controller;

import com.acme.otorongo.domain.model.expenses.Quotation;
import com.acme.otorongo.domain.service.expenses_service.QuotationService;
import com.acme.otorongo.resource.expenses_resource.QuotationResource;
import com.acme.otorongo.resource.save_expenses_resource.SaveQuotationResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class QuotationController {

    private final QuotationService quotationService;
    private final ModelMapper mapper;

    @Autowired
    public QuotationController(QuotationService quotationService, ModelMapper mapper){
        this.quotationService = quotationService;
        this.mapper = mapper;
    }

    @GetMapping("/quotations")
    public List<QuotationResource> getAllQuotations(){
        List<Quotation> quotations = quotationService.getAllQuotations();
        return quotations.stream().map(this::convertToResource).collect(Collectors.toList());
    }

    @GetMapping("/quotations/{quotationId}")
    public QuotationResource getQuotationById(@PathVariable(name = "quotationId") Long quotationId){
        return convertToResource(quotationService.getQuotationById(quotationId));
    }

    @PostMapping("/quotations")
    public QuotationResource createQuotation(@RequestBody SaveQuotationResource quotation){
        Quotation newQuotation = quotationService.createQuotation(convertToEntity(quotation));
        return convertToResource(newQuotation);
    }

    @PutMapping("/quotations/{quotationId}")
    public QuotationResource updateQuotation(@RequestBody SaveQuotationResource quotation,
                                             @PathVariable(name = "quotationId") Long quotationId){
        Quotation updated = quotationService.updateQuotation(quotationId, convertToEntity(quotation));
        return convertToResource(updated);
    }

    @DeleteMapping("/quotations/{quotationId}")
    public ResponseEntity<?> deleteQuotation(@PathVariable(name = "quotationId") Long quotationId){
        return quotationService.deleteQuotation(quotationId);
    }

    private Quotation convertToEntity(SaveQuotationResource resource){
        return mapper.map(resource, Quotation.class);
    }

    private QuotationResource convertToResource(Quotation entity){
        return mapper.map(entity, QuotationResource.class);
    }
}
