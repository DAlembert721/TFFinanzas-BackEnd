package com.acme.otorongo.controller.locations_controller;

import com.acme.otorongo.domain.model.locations.*;
import com.acme.otorongo.domain.service.locations_service.*;
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
public class DistrictController {

    private final DistrictService districtService;
    private final ModelMapper mapper;

    @Autowired
    public DistrictController(DistrictService districtService, ModelMapper mapper){
        this.districtService = districtService;
        this.mapper = mapper;
    }


    @GetMapping("/districts")
    public List<DistrictResource> getAllDistricts()
    {
        List<District> districts = districtService.getAllDistricts();
        return districts.stream().map(this::convertToResource).collect(Collectors.toList());
    }

    @GetMapping("/provinces/{provinceId}/districts")
    public List<DistrictResource> getAllDistrictsByProvinceId(@PathVariable(name = "provinceId") Long provinceId)
    {
        List<District> districts = districtService.getAllDistrictsByProvinceId(provinceId);
        return districts.stream().map(this::convertToResource).collect(Collectors.toList());
    }

    @PostConstruct
    public void init(){
        mapper.addMappings(new PropertyMap<District, DistrictResource>() {
            @Override
            protected void configure() {
                map().setDistrictName(source.getProvince().getName());
            }
        });
    }

    private DistrictResource convertToResource(District entity) {
        return mapper.map(entity, DistrictResource.class);
    }

}
