package com.tads.clientcrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tads.clientcrud.entity.Client;

// REPOSITORY acessa o banco
@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
