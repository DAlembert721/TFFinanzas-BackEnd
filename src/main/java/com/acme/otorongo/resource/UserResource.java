package com.acme.otorongo.resource;

import com.acme.otorongo.domain.model.Account;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserResource {
    @NotNull
    @Column(unique = true)
    private String email;

    @NotNull
    @Size(max = 50)
    private String password;

    @OneToOne(mappedBy = "users")
    private Account account;
}
