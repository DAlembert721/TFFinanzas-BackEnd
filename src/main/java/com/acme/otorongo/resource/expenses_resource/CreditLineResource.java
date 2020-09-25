package com.acme.otorongo.resource.expenses_resource;

import lombok.Data;

@Data
public class CreditLineResource {
    private Long id;
    private Float total;
    private Float balance;
    private String clientName;
}
