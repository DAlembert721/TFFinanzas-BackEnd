package com.acme.otorongo.domain.repository.locations_repository;

import com.acme.otorongo.domain.model.locations.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {
}
