package com.example.clev.controllers;

import com.example.clev.dao.entity.Client;
import com.example.clev.dao.entity.Note;
import com.example.clev.request.NoteRequest;
import com.example.clev.services.OldSystemClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/old-system")
public class OldSystemController {

    private final OldSystemClientService oldSystemClient;

    @Autowired
    public OldSystemController(OldSystemClientService oldSystemClient) {
        this.oldSystemClient = oldSystemClient;
    }

    @PostMapping("/clients")
    public List<Client> getClients() {
        return oldSystemClient.getClients();
    }

    @PostMapping("/notes")
    public List<Note> getNotes(@RequestBody NoteRequest request) {
        return oldSystemClient.getNotes(request.getClientGuid(), request.getAgency(), request.getDateFrom(), request.getDateTo());
    }
}