package com.acme.otorongo.service.locations_service_impl;

import com.acme.otorongo.domain.model.locations.Region;
import com.acme.otorongo.domain.repository.locations_repository.RegionRepository;
import com.acme.otorongo.domain.service.locations_service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionServiceImpl implements RegionService {

    public RegionRepository regionRepository;

    @Autowired
    public RegionServiceImpl(RegionRepository regionRepository){
        this.regionRepository = regionRepository;
    }

    @Override
    public List<Region> getAllRegions() {
        return regionRepository.findAll();
    }
}
