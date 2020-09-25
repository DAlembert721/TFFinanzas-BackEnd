package com.acme.otorongo.domain.service.costs_service;

import com.acme.otorongo.domain.model.costs.InitialCost;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface InitialCostService {
    List<InitialCost> getAllInitialCosts();
    InitialCost createInitialCost(InitialCost initialCost);
    InitialCost getInitialCostById(Long initialCostId);
    InitialCost updateInitialCost(Long initialCostId, InitialCost initialCost);
    ResponseEntity<?> deleteInitialCost(Long initialCostId);
}
