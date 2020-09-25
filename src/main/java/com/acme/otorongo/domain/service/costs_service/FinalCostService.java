package com.acme.otorongo.domain.service.costs_service;

import com.acme.otorongo.domain.model.costs.FinalCost;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FinalCostService {
    List<FinalCost> getAllFinalCosts();
    FinalCost createFinalCost(FinalCost finalCost);
    FinalCost getFinalCostById(Long finalCostId);
    FinalCost updateFinalCost(Long finalCostId, FinalCost finalCost);
    ResponseEntity<?> deleteFinalCost(Long finalCostId);
}
