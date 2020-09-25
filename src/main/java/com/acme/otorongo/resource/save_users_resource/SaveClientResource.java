package com.acme.otorongo.resource.save_users_resource;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class SaveClientResource {
    @NotNull
    @Size(max = 100)
    private String name;
    @NotNull
    private Long phone;
    @NotNull
    private Float balance;
}
