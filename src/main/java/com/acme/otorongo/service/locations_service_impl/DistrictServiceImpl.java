package com.acme.otorongo.service.locations_service_impl;

import com.acme.otorongo.domain.model.locations.District;
import com.acme.otorongo.domain.repository.locations_repository.DistrictRepository;
import com.acme.otorongo.domain.service.locations_service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictServiceImpl implements DistrictService {

    private final DistrictRepository districtRepository;

    @Autowired
    public DistrictServiceImpl(DistrictRepository districtRepository){
        this.districtRepository = districtRepository;
    }


    @Override
    public List<District> getAllDistricts() {
        return districtRepository.findAll();
    }

    @Override
    public List<District> getAllDistrictsByProvinceId(Long provinceId) {
        return districtRepository.findByProvinceId(provinceId);
    }
}
