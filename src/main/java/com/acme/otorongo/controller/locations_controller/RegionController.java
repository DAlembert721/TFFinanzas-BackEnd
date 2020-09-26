package com.acme.otorongo.controller.locations_controller;

import com.acme.otorongo.domain.model.locations.Region;
import com.acme.otorongo.domain.service.locations_service.RegionService;
import com.acme.otorongo.resource.locations_resource.RegionResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class RegionController {

    private final RegionService regionService;
    private final ModelMapper mapper;

    @Autowired
    public RegionController(RegionService regionService, ModelMapper mapper){
        this.regionService = regionService;
        this.mapper = mapper;
    }

    @GetMapping("/regions")
    public List<RegionResource> getAllRegions()
    {
        List<Region> regions = regionService.getAllRegions();
        return regions.stream().map(this::convertToResource).collect(Collectors.toList());
    }

    private RegionResource convertToResource(Region entity) {
        return mapper.map(entity, RegionResource.class);
    }
}
