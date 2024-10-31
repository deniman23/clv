package com.example.clev.dao.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "note")
public class Note {
    @Id
    private String guid;
    private String comments;
    private LocalDateTime createdDateTime;
    private LocalDateTime modifiedDateTime;
    @ManyToOne
    private PatientProfile patient;
    @ManyToOne
    private CompanyUser user;
}
