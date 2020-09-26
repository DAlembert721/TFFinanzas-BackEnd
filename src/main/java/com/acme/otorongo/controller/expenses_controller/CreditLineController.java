package com.acme.otorongo.controller.expenses_controller;

import com.acme.otorongo.domain.model.expenses.CreditLine;
import com.acme.otorongo.domain.service.expenses_service.CreditLineService;
import com.acme.otorongo.resource.expenses_resource.CreditLineResource;
import com.acme.otorongo.resource.save_expenses_resource.SaveCreditLineResource;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CreditLineController {

    private final CreditLineService creditLineService;
    private final ModelMapper mapper;

    @Autowired
    public CreditLineController(CreditLineService creditLineService, ModelMapper mapper){
        this.creditLineService = creditLineService;
        this.mapper = mapper;
    }

    @GetMapping("/creditLines")
    public List<CreditLineResource> getAllCreditLines(){
        List<CreditLine> creditLines = creditLineService.getAllCreditLines();
        return creditLines.stream().map(this::convertToResource).collect(Collectors.toList());
    }

    @GetMapping("/clients/{clientId}/creditLines")
    public List<CreditLineResource> getAllCreditLinesByClientId(@PathVariable(name = "clientId") Long clientId){
        List<CreditLine> creditLines = creditLineService.getAllCreditLinesByClientId(clientId);
        return creditLines.stream().map(this::convertToResource).collect(Collectors.toList());
    }

    @GetMapping("/creditLines/{creditLineId}")
    public CreditLineResource getCreditLineById(@PathVariable(name = "creditLineId") Long creditLineId){
        return convertToResource(creditLineService.getCreditLineById(creditLineId));
    }

    @PostMapping("clients/{clientId}/creditLines")
    public CreditLineResource createCreditLine(@RequestBody SaveCreditLineResource creditLine,
                                               @PathVariable(name = "clientId") Long clientId){
        CreditLine newCreditLine = creditLineService.createCreditLine(convertToEntity(creditLine), clientId);
        return convertToResource(newCreditLine);
    }

    @PutMapping("/creditLines/{creditLineId}")
    public CreditLineResource updateCreditLine(@RequestBody SaveCreditLineResource creditLine,
                                               @PathVariable(name = "creditLineId") Long creditLineId){
        CreditLine updated = creditLineService.updateCreditLine(creditLineId, convertToEntity(creditLine));
        return convertToResource(updated);
    }

    @DeleteMapping("/creditLines/{creditLineId}")
    public ResponseEntity<?> deleteCreditLine(@PathVariable(name = "creditLineId") Long creditLineId){
        return creditLineService.deleteCreditLine(creditLineId);
    }

    @PostConstruct
    public void init(){
        mapper.addMappings(new PropertyMap<CreditLine, CreditLineResource>() {
            @Override
            protected void configure() {
                map().setClientName(source.getClient().getName());
            }
        });
    }

    private CreditLine convertToEntity(SaveCreditLineResource resource){
        return mapper.map(resource, CreditLine.class);
    }

    private CreditLineResource convertToResource(CreditLine entity){
        return mapper.map(entity, CreditLineResource.class);
    }
}
