package com.acme.otorongo.controller.costs_controller;

import com.acme.otorongo.domain.model.costs.InitialCost;
import com.acme.otorongo.domain.service.costs_service.InitialCostService;
import com.acme.otorongo.resource.costs_resource.InitialCostResource;
import com.acme.otorongo.resource.save_costs_resource.SaveInitialCostResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class InitialCostController {

    private final InitialCostService initialCostService;
    private final ModelMapper mapper;

    @Autowired
    public InitialCostController(InitialCostService initialCostService, ModelMapper mapper){
        this.initialCostService = initialCostService;
        this.mapper = mapper;
    }

    @GetMapping("/initialCosts")
    public List<InitialCostResource> getAllInitialCosts(){
        List<InitialCost> initialCosts = initialCostService.getAllInitialCosts();
        return initialCosts.stream().map(this::convertToResource).collect(Collectors.toList());
    }

    @GetMapping("/initialCosts/{initialCostId}")
    public InitialCostResource getInitialCostById(@PathVariable(name = "initialCostId") Long initialCostId){
        return convertToResource(initialCostService.getInitialCostById(initialCostId));
    }

    @PostMapping("/initialCosts")
    public InitialCostResource createInitialCost(@RequestBody SaveInitialCostResource initialCost){
        InitialCost newInitialCost = initialCostService.createInitialCost(convertToEntity(initialCost));
        return convertToResource(newInitialCost);
    }

    @PutMapping("/initialCosts/{initialCostId}")
    public InitialCostResource updateInitialCost(@RequestBody SaveInitialCostResource initialCost,
                                      @PathVariable(name = "initialCostId") Long initialCostId){
        InitialCost updated = initialCostService.updateInitialCost(initialCostId, convertToEntity(initialCost));
        return convertToResource(updated);
    }

    @DeleteMapping("/initialCosts/{initialCostId}")
    public ResponseEntity<?> deleteInitialCost(@PathVariable(name = "initialCostId") Long initialCostId){
        return initialCostService.deleteInitialCost(initialCostId);
    }

    private InitialCost convertToEntity(SaveInitialCostResource resource){
        return mapper.map(resource, InitialCost.class);
    }

    private InitialCostResource convertToResource(InitialCost entity){
        return mapper.map(entity, InitialCostResource.class);
    }
}
