package com.acme.otorongo.controller.promissories_controller;

import com.acme.otorongo.domain.model.promissories.PromissoryNote;
import com.acme.otorongo.domain.service.promissories_service.PromissoryNoteService;
import com.acme.otorongo.resource.promissories_resource.PromissoryNoteResource;
import com.acme.otorongo.resource.save_promissories_resource.SavePromissoryNoteResource;
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
public class PromissoryNoteController {

    private final PromissoryNoteService promissoryNoteService;
    private final ModelMapper mapper;

    @Autowired
    public PromissoryNoteController(PromissoryNoteService promissoryNoteService, ModelMapper mapper){
        this.promissoryNoteService = promissoryNoteService;
        this.mapper = mapper;
    }

    @GetMapping("/promissoryNotes")
    public List<PromissoryNoteResource> getAllPromissoryNotes(){
        List<PromissoryNote> promissoryNotes = promissoryNoteService.getAllPromissoryNotes();
        return promissoryNotes.stream().map(this::convertToResource).collect(Collectors.toList());
    }

    @GetMapping("/rates/{rateId}/promissoryNotes")
    public List<PromissoryNoteResource> getAllPromissoryNotesByRateId(@PathVariable(name = "rateId") Long rateId){
        List<PromissoryNote> promissoryNotes = promissoryNoteService.getAllPromissoryNotesByRateId(rateId);
        return promissoryNotes.stream().map(this::convertToResource).collect(Collectors.toList());
    }

    @GetMapping("/operations/{operationId}/promissoryNotes")
    public PromissoryNoteResource getPromissoryNoteByOperationId(@PathVariable(name = "operationId") Long operationId){
        return convertToResource(promissoryNoteService.getPromissoryNoteByOperationId(operationId));
    }

    @GetMapping("/promissoryNotes/{promissoryNoteId}")
    public PromissoryNoteResource getPromissoryNoteById(@PathVariable(name = "promissoryNoteId")
                                                                    Long promissoryNoteId){
        return convertToResource(promissoryNoteService.getPromissoryNoteId(promissoryNoteId));
    }

    @PostMapping("/rates/{rateId}/operations/{operationId}/promissoryNotes")
    public PromissoryNoteResource createPromissoryNote(@RequestBody SavePromissoryNoteResource promissoryNote,
                                                       @PathVariable(name = "rateId") Long rateId,
                                                       @PathVariable(name = "operationId") Long operationId){
        PromissoryNote newPromissoryNote = promissoryNoteService
                .createPromissoryNote(convertToEntity(promissoryNote), rateId, operationId);
        return convertToResource(newPromissoryNote);
    }

    @PutMapping("/promissoryNotes/{promissoryNoteId}")
    public PromissoryNoteResource updatePromissoryNote(@RequestBody SavePromissoryNoteResource promissoryNote,
                                               @PathVariable(name = "promissoryNoteId") Long promissoryNoteId){
        PromissoryNote updated = promissoryNoteService.updatePromissoryNote(promissoryNoteId,
                convertToEntity(promissoryNote));
        return convertToResource(updated);
    }

    @DeleteMapping("/promissoryNotes/{promissoryNoteId}")
    public ResponseEntity<?> deletePromissoryNote(@PathVariable(name = "promissoryNoteId") Long promissoryNoteId){
        return promissoryNoteService.deletePromissoryNote(promissoryNoteId);
    }

    @PostConstruct
    public void init(){
        mapper.addMappings(new PropertyMap<PromissoryNote, PromissoryNoteResource>() {
            @Override
            protected void configure() {
                map().setRateName(source.getRate().getName());
                map().setOperationId(source.getOperation().getId());
            }
        });
    }

    private PromissoryNote convertToEntity(SavePromissoryNoteResource resource){
        return mapper.map(resource, PromissoryNote.class);
    }

    private PromissoryNoteResource convertToResource(PromissoryNote entity){
        return mapper.map(entity, PromissoryNoteResource.class);
    }
}
