package com.acme.otorongo.resource.save_operations_resource;

import lombok.Data;

import java.util.Date;

@Data
public class SaveOperationResource {
    private Float rateValue;
    private Date discountDate;
    private Date expireDate;
    private Boolean state;
}
