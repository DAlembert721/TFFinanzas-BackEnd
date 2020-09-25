package com.acme.otorongo.resource.save_costs_resource;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class SaveFinalCostResource {
    @NotNull
    @Size(max = 20)
    private String name;
}
