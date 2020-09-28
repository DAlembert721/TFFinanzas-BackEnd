package com.acme.otorongo.controller.operations_controller;

import com.acme.otorongo.domain.model.operations.SimpleRateOperation;
import com.acme.otorongo.domain.service.operations_service.SimpleRateOperationService;
import com.acme.otorongo.resource.operations_resource.SimpleRateOperationResource;
import com.acme.otorongo.resource.save_operations_resource.SaveSimpleRateOperationResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class SimpleRateOperationController {

    private final SimpleRateOperationService simpleRateOperationService;
    private final ModelMapper mapper;

    @Autowired
    public SimpleRateOperationController(SimpleRateOperationService simpleRateOperationService,
                                         ModelMapper mapper){
        this.simpleRateOperationService = simpleRateOperationService;
        this.mapper = mapper;
    }

    @GetMapping("/simpleRateOperations")
    public List<SimpleRateOperationResource> getAllSimpleRateOperations(){
        List<SimpleRateOperation> simpleRateOperations = simpleRateOperationService.getAllSimpleRateOperations();
        return simpleRateOperations.stream().map(this::convertToResource).collect(Collectors.toList());
    }

    @PostMapping("/clients/{clientId}/currencies/{currencyId}/simpleRateOperations")
    public SimpleRateOperationResource createSimpleRateOperation(
            @RequestBody SaveSimpleRateOperationResource simpleRateOperation,
            @PathVariable(name = "clientId") Long clientId,
            @PathVariable(name = "currencyId") Long currencyId){
        SimpleRateOperation newSimpleRateOperation = simpleRateOperationService
                .save(convertToEntity(simpleRateOperation), clientId, currencyId);
        return convertToResource(newSimpleRateOperation);
    }

    @PutMapping("/simpleRateOperations/{simpleRateOperationId}")
    public SimpleRateOperationResource updateSimpleRateOperation(
            @PathVariable(name = "simpleRateOperationId") Long simpleRateOperationId,
            @RequestBody SaveSimpleRateOperationResource saveSimpleRateOperation){
        SimpleRateOperation updated = simpleRateOperationService
                .update(simpleRateOperationId, convertToEntity(saveSimpleRateOperation));
        return convertToResource(updated);
    }

    private SimpleRateOperation convertToEntity(SaveSimpleRateOperationResource resource){
        return mapper.map(resource, SimpleRateOperation.class);
    }

    private SimpleRateOperationResource convertToResource(SimpleRateOperation entity){
        return mapper.map(entity, SimpleRateOperationResource.class);
    }
}
