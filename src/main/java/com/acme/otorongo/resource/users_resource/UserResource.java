package com.acme.otorongo.resource.users_resource;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserResource {
    private String email;
    private String password;
    private String lastName;
    private String dni;
    private Long phone;
}
