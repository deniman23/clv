package com.example.clev.dao.repository;

import com.example.clev.dao.entity.PatientNote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientNoteRepository extends JpaRepository<PatientNote, Long> {
    Optional<PatientNote> findByGuid(String guid);
}