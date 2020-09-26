package com.acme.otorongo.controller.users_controller;

import com.acme.otorongo.domain.model.users.Client;
import com.acme.otorongo.domain.service.users_service.ClientService;
import com.acme.otorongo.resource.save_users_resource.SaveClientResource;
import com.acme.otorongo.resource.users_resource.ClientResource;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ClientController {

    private final ClientService clientService;
    private final ModelMapper mapper;

    @Autowired
    public ClientController(ClientService clientService, ModelMapper mapper){
        this.clientService = clientService;
        this.mapper = mapper;
    }

    @GetMapping("/clients")
    public List<ClientResource> getAllClients()
    {
        List<Client> clients = clientService.getAllClients();
        return clients.stream().map(this::convertToResource).collect(Collectors.toList());
    }

    @GetMapping("/districts/{districtId}/clients")
    public List<ClientResource> getAllClientsByDistrictId(@PathVariable(name = "districtId") Long districtId)
    {
        List<Client> clients = clientService.getAllClientsByDistrictId(districtId);
        return clients.stream().map(this::convertToResource).collect(Collectors.toList());
    }

    @GetMapping("/users/{userId}/clients")
    public List<ClientResource> getAllClientsByUserId(@PathVariable(name = "userId") Long userId)
    {
        List<Client> clients = clientService.getAllClientsByUserId(userId);
        return clients.stream().map(this::convertToResource).collect(Collectors.toList());
    }

    @GetMapping("/clients/{clientId}")
    public ClientResource getLotById(@PathVariable(name = "clientId") Long clientId)
    {
        return convertToResource(clientService.getClientById(clientId));
    }

    @PostMapping("/districts/{districtId}/users/{userId}/clients")
    public ClientResource create(@RequestBody SaveClientResource client,
                              @PathVariable(name = "districtId") Long districtId,
                              @PathVariable(name = "userId") Long userId)
    {
        Client newClient = clientService.createClient(convertToEntity(client), userId, districtId);
        return convertToResource(newClient);
    }

    @PutMapping("/clients/{clientId}")
    public ClientResource update(@RequestBody SaveClientResource client,
                              @PathVariable(name = "clientId") Long clientId)
    {
        Client updated = clientService.updateClient(clientId, convertToEntity(client));
        return convertToResource(updated);
    }

    @DeleteMapping("/clients/{clientId}")
    public void deleteClient(@PathVariable(name = "clientId") Long clientId)
    {
        clientService.deleteClient(clientId);
    }

    @PostConstruct
    public void init(){
        mapper.addMappings(new PropertyMap<Client, ClientResource>() {
            @Override
            protected void configure() {
                map().setUserName(source.getUser().getFirstName());
                map().setDistrictName(source.getDistrict().getName());
            }
        });
    }

    private Client convertToEntity(SaveClientResource resource) {
        return mapper.map(resource, Client.class);
    }

    private ClientResource convertToResource(Client entity) {
        return mapper.map(entity, ClientResource.class);
    }

}
