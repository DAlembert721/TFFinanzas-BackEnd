package com.acme.otorongo.domain.repository.locations_repository;

import com.acme.otorongo.domain.model.locations.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Long> {
    List<Province> findByRegionId(Long regionId);
}
