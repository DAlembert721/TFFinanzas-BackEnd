package com.acme.otorongo.resource.save_operations_resource;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SaveAnnuityResource extends SaveOperationResource{
    private Float initialQuota;
    private Float capital;
    private Float future;
}
