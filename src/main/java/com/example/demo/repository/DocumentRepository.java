package com.example.demo.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.Document;

public interface DocumentRepository extends JpaRepository<Document, Serializable>{

	Document findByDocumentId(String documentId);
}
