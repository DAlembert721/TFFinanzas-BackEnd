package com.acme.otorongo.controller.locations_controller;

import com.acme.otorongo.domain.model.locations.Province;
import com.acme.otorongo.domain.service.locations_service.ProvinceService;
import com.acme.otorongo.resource.locations_resource.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ProvinceController {

    private final ProvinceService provinceService;
    private final ModelMapper mapper;

    @Autowired
    public ProvinceController(ProvinceService provinceService, ModelMapper mapper){
        this.provinceService = provinceService;
        this.mapper = mapper;
    }

    @GetMapping("/provinces")
    public List<ProvinceResource> getAllProvinces()
    {
        List<Province> provinces = provinceService.getAllProvinces();
        return provinces.stream().map(this::convertToResource).collect(Collectors.toList());
    }

    @GetMapping("/regions/{regionId}/provinces")
    public List<ProvinceResource> getAllProvincesByRegionId(@PathVariable(name = "regionId") Long regionId)
    {
        List<Province> provinces = provinceService.getAllProvincesByRegionId(regionId);
        return provinces.stream().map(this::convertToResource).collect(Collectors.toList());
    }

    @PostConstruct
    public void init(){
        mapper.addMappings(new PropertyMap<Province, ProvinceResource>() {
            @Override
            protected void configure() {
                map().setRegionName(source.getRegion().getName());
            }
        });
    }

    private ProvinceResource convertToResource(Province entity) {
        return mapper.map(entity, ProvinceResource.class);
    }
}
