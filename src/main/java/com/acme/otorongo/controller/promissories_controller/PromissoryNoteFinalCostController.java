package com.acme.otorongo.controller.promissories_controller;

import com.acme.otorongo.domain.model.promissories.PromissoryNoteFinalCost;
import com.acme.otorongo.domain.service.promissories_service.PromissoryNoteFinalCostService;
import com.acme.otorongo.resource.promissories_resource.PromissoryNoteFinalCostResource;
import com.acme.otorongo.resource.save_promissories_resource.SavePromissoryNoteFinalCostResource;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class PromissoryNoteFinalCostController {
    private final PromissoryNoteFinalCostService promissoryNoteFinalCostService;
    private final ModelMapper mapper;

    @Autowired
    public PromissoryNoteFinalCostController(PromissoryNoteFinalCostService promissoryNoteFinalCostService,
                                               ModelMapper mapper) {
        this.promissoryNoteFinalCostService = promissoryNoteFinalCostService;
        this.mapper = mapper;
    }

    @GetMapping("/promissoryNotes/{promissoryNoteId}/promissoryNoteFinalCosts")
    public List<PromissoryNoteFinalCostResource> getAllPromissoryNoteFinalCostsByPromissoryNoteId(
            @PathVariable(name = "promissoryNoteId") Long promissoryNoteId)
    {
        List<PromissoryNoteFinalCost> promissoryNoteFinalCosts =
                promissoryNoteFinalCostService.getAllPromissoryNoteFinalsByPromissoryNoteId(promissoryNoteId);
        return promissoryNoteFinalCosts.stream().map(this::convertToResource).collect(Collectors.toList());
    }

    @GetMapping("/finalCosts/{finalCostId}/promissoryNoteFinalCosts")
    public List<PromissoryNoteFinalCostResource> getAllPromissoryNoteFinalCostsByFinalCostId(
            @PathVariable(name = "finalCostId") Long finalCostId)
    {
        List<PromissoryNoteFinalCost> promissoryNoteFinalCosts =
                promissoryNoteFinalCostService.getAllPromissoryNoteFinalsByFinalCostId(finalCostId);
        return promissoryNoteFinalCosts.stream().map(this::convertToResource).collect(Collectors.toList());
    }

    @PostMapping("/promissoryNotes/{promissoryNoteId}/finalCosts/{finalCostId}")
    public PromissoryNoteFinalCostResource create(
            @RequestBody SavePromissoryNoteFinalCostResource promissoryNoteFinalCost,
            @PathVariable(name = "promissoryNoteId") Long promissoryNoteId,
            @PathVariable(name = "finalCostId") Long finalCostId)
    {
        PromissoryNoteFinalCost newPromissoryNoteFinalCost = promissoryNoteFinalCostService
                .save(convertToEntity(promissoryNoteFinalCost), promissoryNoteId, finalCostId);
        return convertToResource(newPromissoryNoteFinalCost);
    }

    @PutMapping("/promissoryNotes/{promissoryNoteId}/finalCosts/{finalCostId}")
    public PromissoryNoteFinalCostResource update(
            @RequestBody SavePromissoryNoteFinalCostResource promissoryNoteFinalCost,
            @PathVariable(name = "promissoryNoteId") Long promissoryNoteId,
            @PathVariable(name = "finalCostId") Long finalCostId)
    {
        PromissoryNoteFinalCost existed = promissoryNoteFinalCostService
                .update(promissoryNoteId, finalCostId, convertToEntity(promissoryNoteFinalCost));
        return convertToResource(existed);
    }

    @DeleteMapping("/promissoryNotes/{promissoryNoteId}/finalCosts/{finalCostId}")
    public void delete(@PathVariable(name = "promissoryNoteId") Long promissoryNoteId,
                       @PathVariable(name = "finalCostId") Long finalCostId)
    {
        promissoryNoteFinalCostService.delete(promissoryNoteId, finalCostId);
    }

    @PostConstruct
    public void init(){
        mapper.addMappings(new PropertyMap<PromissoryNoteFinalCost, PromissoryNoteFinalCostResource>() {
            @Override
            protected void configure(){
                map().setPromissoryNoteId(source.getPromissoryNote().getId());
                map().setFinalCostName(source.getFinalCost().getName());
            }
        });
    }

    private PromissoryNoteFinalCost convertToEntity(SavePromissoryNoteFinalCostResource resource) {
        return mapper.map(resource, PromissoryNoteFinalCost.class);
    }

    private PromissoryNoteFinalCostResource convertToResource(PromissoryNoteFinalCost entity){
        return mapper.map(entity, PromissoryNoteFinalCostResource.class);
    }

}
