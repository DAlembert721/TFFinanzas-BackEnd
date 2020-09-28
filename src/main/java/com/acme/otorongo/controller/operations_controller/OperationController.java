package com.acme.otorongo.controller.operations_controller;

import com.acme.otorongo.domain.model.operations.Operation;
import com.acme.otorongo.domain.service.operations_service.OperationService;
import com.acme.otorongo.resource.operations_resource.OperationResource;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class OperationController {
    private final OperationService operationService;
    private final ModelMapper mapper;

    @Autowired
    public OperationController(OperationService operationService, ModelMapper mapper){
        this.operationService = operationService;
        this.mapper = mapper;
    }

    @GetMapping("/operations")
    public List<OperationResource> getAllOperations(){
        List<Operation> operations = operationService.getAllOperations();
        return operations.stream().map(this::convertToResource).collect(Collectors.toList());
    }

    @GetMapping("/clients/{clientId}/operations")
    public List<OperationResource> getAllOperationsByClientId(@PathVariable(name = "clientId") Long clientId){
        List<Operation> operations = operationService.getAllOperationsByClientId(clientId);
        return operations.stream().map(this::convertToResource).collect(Collectors.toList());
    }

    @GetMapping("/currencies/{currencyId}/operations")
    public List<OperationResource> getAllOperationsByCurrencyId(@PathVariable(name = "currencyId") Long currencyId){
        List<Operation> operations = operationService.getAllOperationsByCurrencyId(currencyId);
        return operations.stream().map(this::convertToResource).collect(Collectors.toList());
    }

    @GetMapping("/operations/{operationId}")
    public OperationResource getOperationById(@PathVariable(name = "operationId") Long operationId){
        return convertToResource(operationService.getOperationId(operationId));
    }

    @DeleteMapping("/operations/{operationId}")
    public ResponseEntity<?> deleteOperation(@PathVariable(name = "operationId") Long operationId){
        return operationService.deleteOperation(operationId);
    }

    @PostConstruct
    public void init(){
        mapper.addMappings(new PropertyMap<Operation, OperationResource>() {
            @Override
            protected void configure() {
                map().setClientName(source.getClient().getName());
                map().setCurrencyName(source.getCurrency().getName());
            }
        });
    }

    private OperationResource convertToResource(Operation entity){
        return mapper.map(entity, OperationResource.class);
    }
}
