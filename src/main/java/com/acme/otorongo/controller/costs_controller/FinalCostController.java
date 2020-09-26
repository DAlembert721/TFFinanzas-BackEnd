package com.acme.otorongo.controller.costs_controller;

import com.acme.otorongo.domain.model.costs.FinalCost;
import com.acme.otorongo.domain.service.costs_service.FinalCostService;
import com.acme.otorongo.resource.costs_resource.FinalCostResource;
import com.acme.otorongo.resource.save_costs_resource.SaveFinalCostResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class FinalCostController {

    private final FinalCostService finalCostService;
    private final ModelMapper mapper;

    @Autowired
    public FinalCostController(FinalCostService finalCostService, ModelMapper mapper){
        this.finalCostService = finalCostService;
        this.mapper = mapper;
    }

    @GetMapping("/finalCosts")
    public List<FinalCostResource> getAllFinalCosts(){
        List<FinalCost> finalCosts = finalCostService.getAllFinalCosts();
        return finalCosts.stream().map(this::convertToResource).collect(Collectors.toList());
    }

    @GetMapping("/finalCosts/{finalCostId}")
    public FinalCostResource getFinalCostById(@PathVariable(name = "finalCostId") Long finalCostId){
        return convertToResource(finalCostService.getFinalCostById(finalCostId));
    }

    @PostMapping("/finalCosts")
    public FinalCostResource createFinalCost(@RequestBody SaveFinalCostResource finalCost){
        FinalCost newFinalCost = finalCostService.createFinalCost(convertToEntity(finalCost));
        return convertToResource(newFinalCost);
    }

    @PutMapping("/finalCosts/{finalCostId}")
    public FinalCostResource updateFinalCost(@RequestBody SaveFinalCostResource finalCost,
                                                 @PathVariable(name = "finalCostId") Long finalCostId){
        FinalCost updated = finalCostService.updateFinalCost(finalCostId, convertToEntity(finalCost));
        return convertToResource(updated);
    }

    @DeleteMapping("/finalCosts/{finalCostId}")
    public void deleteFinalCost(@PathVariable(name = "finalCostId") Long finalCostId){
        finalCostService.deleteFinalCost(finalCostId);
    }

    private FinalCost convertToEntity(SaveFinalCostResource resource){
        return mapper.map(resource, FinalCost.class);
    }

    private FinalCostResource convertToResource(FinalCost entity){
        return mapper.map(entity, FinalCostResource.class);
    }
}
