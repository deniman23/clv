package com.example.clev.dao.repository;

import com.example.clev.dao.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByGuid(String guid);
}
