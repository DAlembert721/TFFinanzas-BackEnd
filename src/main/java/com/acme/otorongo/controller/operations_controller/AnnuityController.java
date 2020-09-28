package com.acme.otorongo.controller.operations_controller;

import com.acme.otorongo.domain.model.operations.Annuity;
import com.acme.otorongo.domain.service.operations_service.AnnuityService;
import com.acme.otorongo.resource.operations_resource.AnnuityResource;
import com.acme.otorongo.resource.save_operations_resource.SaveAnnuityResource;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class AnnuityController {

    private final AnnuityService annuityService;
    private final ModelMapper mapper;

    @Autowired
    public AnnuityController(AnnuityService annuityService, ModelMapper mapper){
        this.annuityService = annuityService;
        this.mapper = mapper;
    }

    @GetMapping("/annuities")
    public List<AnnuityResource> getAllAnnuities(){
        List<Annuity> annuities = annuityService.getAllAnnuities();
        return annuities.stream().map(this::convertToResource).collect(Collectors.toList());
    }

    @GetMapping("/rates/{rateId}/annuities")
    public List<AnnuityResource> getAllAnnuitiesByRateId(@PathVariable(name = "rateId") Long rateId){
        List<Annuity> annuities = annuityService.getAllAnnuitiesByRateId(rateId);
        return annuities.stream().map(this::convertToResource).collect(Collectors.toList());
    }

    @GetMapping("/quotation/{quotationId}/annuities")
    public List<AnnuityResource> getAllAnnuitiesByQuotationId(@PathVariable(name = "quotationId") Long quotationId){
        List<Annuity> annuities = annuityService.getAllAnnuitiesByQuotationId(quotationId);
        return annuities.stream().map(this::convertToResource).collect(Collectors.toList());
    }

    @PostMapping("/clients/{clientId}/currencies/{currencyId}/quotations/{quotationId}/rates/{rateId}/annuities")
    public AnnuityResource createAnnuity(@RequestBody SaveAnnuityResource annuity,
                                         @PathVariable(name = "clientId") Long clientId,
                                         @PathVariable(name = "currencyId") Long currencyId,
                                         @PathVariable(name = "quotationId") Long quotationId,
                                         @PathVariable(name = "rateId") Long rateId){
        Annuity newAnnuity = annuityService.save(convertToEntity(annuity), clientId, currencyId, quotationId, rateId);
        return convertToResource(newAnnuity);
    }

    @PutMapping("/annuities/{annuityId}")
    public AnnuityResource updateAnnuity(@PathVariable(name = "annuityId") Long annuityId,
                                         @RequestBody SaveAnnuityResource annuity){
        Annuity updated = annuityService.update(annuityId, convertToEntity(annuity));
        return convertToResource(updated);
    }

    @PostConstruct
    public void init(){
        mapper.addMappings(new PropertyMap<Annuity, AnnuityResource>() {
            @Override
            protected void configure() {
                map().setQuotationName(source.getQuotation().getName());
                map().setRateName(source.getRate().getName());
            }
        });
    }

    private Annuity convertToEntity(SaveAnnuityResource resource){
        return mapper.map(resource, Annuity.class);
    }

    private AnnuityResource convertToResource(Annuity entity){
        return mapper.map(entity, AnnuityResource.class);
    }
}
