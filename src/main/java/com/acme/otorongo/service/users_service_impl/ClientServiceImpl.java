package com.acme.otorongo.service.users_service_impl;

import com.acme.otorongo.domain.model.users.Client;
import com.acme.otorongo.domain.model.users.User;
import com.acme.otorongo.domain.repository.locations_repository.DistrictRepository;
import com.acme.otorongo.domain.repository.users_repository.ClientRepository;
import com.acme.otorongo.domain.repository.users_repository.UserRepository;
import com.acme.otorongo.domain.service.users_service.ClientService;
import com.acme.otorongo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final UserRepository userRepository;
    private final DistrictRepository districtRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, UserRepository userRepository, DistrictRepository districtRepository){
        this.clientRepository = clientRepository;
        this.userRepository = userRepository;
        this.districtRepository = districtRepository;
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public List<Client> getAllClientsByUserId(Long userId) {
        return clientRepository.findByUserId(userId);
    }

    @Override
    public List<Client> getAllClientsByDistrictId(Long districtId) {
        return clientRepository.findByDistrictId(districtId);
    }

    @Override
    public Client createClient(Client client, Long userId, Long districtId) {
        client.setUser(userRepository.findById(userId).orElse(null));
        client.setDistrict(districtRepository.findById(districtId).orElse(null));
        return clientRepository.save(client);
    }

    @Override
    public Client getClientById(Long clientId) {
        return clientRepository.findById(clientId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Client", "Id", clientId));
    }

    @Override
    public Client updateClient(Long clientId, Client client) {
        Client existed = clientRepository.findById(clientId).orElse(null);
        if (existed == null)
            return null;
        existed.setName(client.getName());
        existed.setPhone(client.getPhone());
        existed.setBalance(client.getBalance());
        return clientRepository.save(existed);
    }

    @Override
    public ResponseEntity<?> deleteClient(Long clientId) {
        clientRepository.deleteById(clientId);
        return ResponseEntity.ok().build();
    }
}
