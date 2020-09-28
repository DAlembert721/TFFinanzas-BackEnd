package com.acme.otorongo.resource.operations_resource;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SimpleRateOperationResource extends OperationResource{
    private Float capital;
    private Float future;
}
