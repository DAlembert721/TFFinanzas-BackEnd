package com.acme.otorongo.domain.service.locations_service;

import com.acme.otorongo.domain.model.locations.District;
import com.acme.otorongo.domain.model.locations.Region;

import java.util.List;

public interface RegionService {
    List<Region> getAllRegions();
}
