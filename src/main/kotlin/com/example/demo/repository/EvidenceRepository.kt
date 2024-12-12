package com.example.demo.repository

import com.example.demo.model.Evidence
import org.springframework.data.jpa.repository.JpaRepository

interface EvidenceRepository: JpaRepository<Evidence, Long>