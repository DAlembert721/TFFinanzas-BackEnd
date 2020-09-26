package com.acme.otorongo.controller.promissories_controller;

import com.acme.otorongo.domain.model.promissories.PromissoryNoteInitialCost;
import com.acme.otorongo.domain.service.promissories_service.PromissoryNoteInitialCostService;
import com.acme.otorongo.resource.promissories_resource.PromissoryNoteInitialCostResource;
import com.acme.otorongo.resource.save_promissories_resource.SavePromissoryNoteInitialCostResource;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class PromissoryNoteInitialCostController {

    private final PromissoryNoteInitialCostService promissoryNoteInitialCostService;
    private final ModelMapper mapper;

    @Autowired
    public PromissoryNoteInitialCostController(PromissoryNoteInitialCostService promissoryNoteInitialCostService,
                                               ModelMapper mapper) {
        this.promissoryNoteInitialCostService = promissoryNoteInitialCostService;
        this.mapper = mapper;
    }

    @GetMapping("/promissoryNotes/{promissoryNoteId}/promissoryNoteInitialCosts")
    public List<PromissoryNoteInitialCostResource> getAllPromissoryNoteInitialCostsByPromissoryNoteId(
            @PathVariable(name = "promissoryNoteId") Long promissoryNoteId)
    {
        List<PromissoryNoteInitialCost> promissoryNoteInitialCosts =
                promissoryNoteInitialCostService.getAllPromissoryNoteInitialsByPromissoryNoteId(promissoryNoteId);
        return promissoryNoteInitialCosts.stream().map(this::convertToResource).collect(Collectors.toList());
    }

    @GetMapping("/initialCosts/{initialCostId}/promissoryNoteInitialCosts")
    public List<PromissoryNoteInitialCostResource> getAllPromissoryNoteInitialCostsByInitialCostId(
            @PathVariable(name = "initialCostId") Long initialCostId)
    {
        List<PromissoryNoteInitialCost> promissoryNoteInitialCosts =
                promissoryNoteInitialCostService.getAllPromissoryNoteInitialsByInitialCostId(initialCostId);
        return promissoryNoteInitialCosts.stream().map(this::convertToResource).collect(Collectors.toList());
    }

    @PostMapping("/promissoryNotes/{promissoryNoteId}/initialCosts/{initialCostId}")
    public PromissoryNoteInitialCostResource create(
            @RequestBody SavePromissoryNoteInitialCostResource promissoryNoteInitialCost,
            @PathVariable(name = "promissoryNoteId") Long promissoryNoteId,
            @PathVariable(name = "initialCostId") Long initialCostId)
    {
        PromissoryNoteInitialCost newPromissoryNoteInitialCost = promissoryNoteInitialCostService
                .save(convertToEntity(promissoryNoteInitialCost), promissoryNoteId, initialCostId);
        return convertToResource(newPromissoryNoteInitialCost);
    }

    @PutMapping("/promissoryNotes/{promissoryNoteId}/initialCosts/{initialCostId}")
    public PromissoryNoteInitialCostResource update(
            @RequestBody SavePromissoryNoteInitialCostResource promissoryNoteInitialCost,
            @PathVariable(name = "promissoryNoteId") Long promissoryNoteId,
            @PathVariable(name = "initialCostId") Long initialCostId)
    {
        PromissoryNoteInitialCost existed = promissoryNoteInitialCostService
                .update(promissoryNoteId, initialCostId, convertToEntity(promissoryNoteInitialCost));
        return convertToResource(existed);
    }

    @DeleteMapping("/promissoryNotes/{promissoryNoteId}/initialCosts/{initialCostId}")
    public void delete(@PathVariable(name = "promissoryNoteId") Long promissoryNoteId,
                                 @PathVariable(name = "initialCostId") Long initialCostId)
    {
        promissoryNoteInitialCostService.delete(promissoryNoteId, initialCostId);
    }

    @PostConstruct
    public void init(){
        mapper.addMappings(new PropertyMap<PromissoryNoteInitialCost, PromissoryNoteInitialCostResource>() {
            @Override
            protected void configure(){
                map().setPromissoryNoteId(source.getPromissoryNote().getId());
                map().setInitialCostName(source.getInitialCost().getName());
            }
        });
    }

    private PromissoryNoteInitialCost convertToEntity(SavePromissoryNoteInitialCostResource resource) {
        return mapper.map(resource, PromissoryNoteInitialCost.class);
    }

    private PromissoryNoteInitialCostResource convertToResource(PromissoryNoteInitialCost entity){
        return mapper.map(entity, PromissoryNoteInitialCostResource.class);
    }
}
