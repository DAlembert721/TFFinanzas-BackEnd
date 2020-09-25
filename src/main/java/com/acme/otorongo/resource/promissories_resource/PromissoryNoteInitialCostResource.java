package com.acme.otorongo.resource.promissories_resource;

import lombok.Data;

@Data
public class PromissoryNoteInitialCostResource {
    private String initialCostName;
    private Long promissoryNoteId;
    private float cost;
}
