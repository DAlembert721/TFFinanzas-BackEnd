package com.acme.otorongo.resource.save_operations_resource;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SaveSimpleRateOperationResource extends SaveOperationResource{
    private Float capital;
    private Float future;
}
