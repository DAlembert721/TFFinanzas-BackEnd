package com.acme.otorongo.resource.operations_resource;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CompoundRateOperationResource extends OperationResource{
    private Float capital;
    private Float future;
    private String rateName;
    private String quotationName;
}
