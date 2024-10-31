package com.example.clev.dao.repository;

import com.example.clev.dao.entity.PatientProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientProfileRepository extends JpaRepository<PatientProfile, Long> {
    Optional<PatientProfile> findByOldClientGuidContaining(String guid);
}
