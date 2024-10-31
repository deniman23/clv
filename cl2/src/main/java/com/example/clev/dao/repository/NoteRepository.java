package com.example.clev.dao.repository;

import com.example.clev.dao.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NoteRepository extends JpaRepository<Note, String> {
    Optional<Note> findByGuid(String guid);
}
