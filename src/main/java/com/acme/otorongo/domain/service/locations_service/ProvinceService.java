package com.acme.otorongo.domain.service.locations_service;

import com.acme.otorongo.domain.model.locations.Province;

import java.util.List;

public interface ProvinceService {
    List<Province> getAllProvinces();
    List<Province> getAllProvincesByRegionId(Long regionId);
}
