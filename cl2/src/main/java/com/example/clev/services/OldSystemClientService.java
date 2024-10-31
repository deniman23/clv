package com.example.clev.services;

import com.example.clev.dao.entity.Client;
import com.example.clev.dao.entity.Note;
import com.example.clev.dao.repository.ClientRepository;
import com.example.clev.request.NoteRequest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class OldSystemClientService {
    private final RestTemplate restTemplate;
    private final ClientRepository clientRepository;

    public OldSystemClientService(RestTemplate restTemplate, ClientRepository clientRepository) {
        this.restTemplate = restTemplate;
        this.clientRepository = clientRepository;
    }

    public List<Client> getClients() {
        List<Client> clients = restTemplate.exchange(
                "/clients",
                HttpMethod.POST,
                null,
                new ParameterizedTypeReference<List<Client>>() {}
        ).getBody();

        if (clients != null) {
            for (Client client : clients) {
                if (clientRepository.findByGuid(client.getGuid()) == null) {
                    clientRepository.save(client);
                }
            }
        }

        return clients;
    }

    public List<Note> getNotes(String clientGuid, String agency, String dateFrom, String dateTo) {
        NoteRequest request = new NoteRequest(clientGuid, agency, dateFrom, dateTo);
        return restTemplate.exchange(
                "/notes",
                HttpMethod.POST,
                new HttpEntity<>(request),
                new ParameterizedTypeReference<List<Note>>() {}
        ).getBody();
    }
}

