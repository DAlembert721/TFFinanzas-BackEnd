package com.acme.otorongo.domain.model.users;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String email;

    @NotNull
    @Size(max = 50)
    private String password;

    @NotNull
    @Size(max = 20)
    private String firstName;

    @NotNull
    @Size(max = 20)
    private String lastName;

    @NotNull
    @Size(max = 10 )
    private String dni;

    @NotNull
    private Long phone;


}
