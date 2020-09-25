package com.acme.otorongo.resource.users_resource;

import lombok.Data;

@Data
public class ClientResource {
    private Long id;
    private String name;
    private Long phone;
    private Float balance;
    private String userName;
    private String districtName;
}
