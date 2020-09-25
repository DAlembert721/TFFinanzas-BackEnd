package com.acme.otorongo.resource.expenses_resource;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CurrencyResource {
    private Long id;
    private String name;
    private String denomination;
}
