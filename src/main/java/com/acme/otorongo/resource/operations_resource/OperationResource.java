package com.acme.otorongo.resource.operations_resource;

import lombok.Data;
import java.util.Date;

@Data
public class OperationResource {
    private Long id;
    private Float rateValue;
    private Date discountDate;
    private Date expireDate;
    private Boolean state;
    private String currencyName;
    private String clientName;
}
