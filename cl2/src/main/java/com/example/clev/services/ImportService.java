package com.example.clev.services;

import com.example.clev.dao.entity.*;
import com.example.clev.dao.repository.CompanyUserRepository;
import com.example.clev.dao.repository.NoteRepository;
import com.example.clev.dao.repository.PatientProfileRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ImportService {
    private final OldSystemClientService oldSystemClientService;
    private final PatientProfileRepository patientProfileRepository;
    private final CompanyUserRepository companyUserRepository;
    private final NoteRepository noteRepository;

    public ImportService(OldSystemClientService oldSystemClientService, PatientProfileRepository patientProfileRepository, CompanyUserRepository companyUserRepository, NoteRepository noteRepository) {
        this.oldSystemClientService = oldSystemClientService;
        this.patientProfileRepository = patientProfileRepository;
        this.companyUserRepository = companyUserRepository;
        this.noteRepository = noteRepository;
    }

    @Scheduled(cron = "0 15 */2 * * *")
    @Transactional
    public void importNotes() {
        List<Client> clients = oldSystemClientService.getClients();
        for (Client client : clients) {
            PatientProfile patient = findPatientByOldClientGuid(client.getGuid());
            if (patient != null && isActive(patient)) {
                String dateFrom = "2019-09-18";
                String dateTo = "2021-09-17";
                List<Note> notes = oldSystemClientService.getNotes(client.getGuid(), client.getAgency(), dateFrom, dateTo);
                for (Note note : notes) {
                    importNote(note, patient);
                }
            }
        }
    }

    private PatientProfile findPatientByOldClientGuid(String guid) {
        return patientProfileRepository.findByOldClientGuidContaining(guid).orElse(null);
    }

    private boolean isActive(PatientProfile patient) {
        return List.of((short) 200, (short) 210, (short) 230).contains(patient.getStatusId());
    }

    private void importNote(Note note, PatientProfile patient) {
        Note existingNote = noteRepository.findByGuid(note.getGuid()).orElse(null);
        if (existingNote == null || note.getModifiedDateTime().isAfter(existingNote.getModifiedDateTime())) {
            CompanyUser user = findOrCreateUser(note.getUser().getLogin());
            note.setPatient(patient);
            note.setUser(user);
            noteRepository.save(note);
        }
    }

    private CompanyUser findOrCreateUser(String login) {
        return companyUserRepository.findByLogin(login).orElseGet(() -> {
            CompanyUser user = new CompanyUser();
            user.setLogin(login);
            return companyUserRepository.save(user);
        });
    }
}