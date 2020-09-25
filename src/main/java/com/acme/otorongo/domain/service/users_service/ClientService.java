package com.acme.otorongo.domain.service.users_service;

import com.acme.otorongo.domain.model.users.Client;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClientService {
    List<Client> getAllClients();
    Client createClient(Client client);
    Client getClientById(Long clientId);
    Client updateClient(Long clientId, Client client);
    ResponseEntity<?> deleteClient(Long clientId);
}
