package com.acme.otorongo.resource.save_expenses_resource;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class SaveCurrencyResource {
    @NotNull
    @Size(max = 250)
    private String name;

    @NotNull
    @Size(max = 4)
    private String denomination;
}
