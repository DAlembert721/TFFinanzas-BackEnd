package com.acme.otorongo.service.locations_service_impl;

import com.acme.otorongo.domain.model.locations.District;
import com.acme.otorongo.domain.model.locations.Province;
import com.acme.otorongo.domain.repository.locations_repository.ProvinceRepository;
import com.acme.otorongo.domain.service.locations_service.ProvinceService;
import org.apache.tomcat.jni.Proc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinceServiceImpl implements ProvinceService {

    private final ProvinceRepository provinceRepository;

    @Autowired
    public ProvinceServiceImpl(ProvinceRepository provinceRepository){
        this.provinceRepository = provinceRepository;
    }

    @Override
    public List<Province> getAllProvinces() {
        return provinceRepository.findAll();
    }

    @Override
    public List<Province> getAllProvincesByRegionId(Long regionId) {
        return provinceRepository.findByRegionId(regionId);
    }
}
