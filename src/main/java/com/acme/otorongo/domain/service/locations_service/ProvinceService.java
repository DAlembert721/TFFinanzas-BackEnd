package com.acme.otorongo.domain.service.locations_service;

import com.acme.otorongo.domain.model.locations.District;

import java.util.List;

public interface ProvinceService {
    List<District> getAllProvinces();
    List<District> getAllProvincesByRegionId(Long regionId);
}
