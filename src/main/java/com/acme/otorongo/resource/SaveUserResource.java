package com.acme.otorongo.resource;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class SaveUserResource {

    @NotNull
    @Column(unique = true)
    private String email;

    @NotNull
    @Size(max = 50)
    private String password;

}
