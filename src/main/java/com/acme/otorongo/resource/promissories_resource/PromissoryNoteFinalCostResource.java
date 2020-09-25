package com.acme.otorongo.resource.promissories_resource;

import lombok.Data;

@Data
public class PromissoryNoteFinalCostResource {
    private String finalCostName;
    private Long promissoryNoteId;
    private float cost;
}
