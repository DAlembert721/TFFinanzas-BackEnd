package com.acme.otorongo.resource.save_expenses_resource;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SaveCreditLineResource {
    @NotNull
    private Float total;
    @NotNull
    private Float balance;
}
