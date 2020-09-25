package com.acme.otorongo.domain.service.locations_service;

import com.acme.otorongo.domain.model.locations.District;
import java.util.List;

public interface DistrictService {
    List<District> getAllDistricts();
    List<District> getAllDistrictsByProvinceId(Long provinceId);
}
