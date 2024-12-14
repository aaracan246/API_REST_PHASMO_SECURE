package com.example.demo.service

import com.example.demo.error.exception.BadRequestException
import com.example.demo.error.exception.NotFoundException
import com.example.demo.model.Evidence
import com.example.demo.repository.EvidenceRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class EvidenceService {

    @Autowired
    private lateinit var evidenceRepository: EvidenceRepository

    fun getAll(): List<Evidence> = evidenceRepository.findAll()

    fun insert(newEvidence: Evidence): Any {
        when{
            newEvidence.name.isBlank() -> throw BadRequestException("Username cannot be empty.")

            newEvidence.ghost.isEmpty()  -> throw BadRequestException("This evidence must appear in at least one ghost.")
        }

        evidenceRepository.save(newEvidence)

        return newEvidence
    }

    fun updateEvidence(evidence: Evidence): Evidence {

        val existingEvidence = evidenceRepository.findById(evidence.id!!).orElseThrow{ throw NotFoundException("Could not find requested evidence by id.")}

        when{
            evidence.name.isBlank() -> throw BadRequestException("Username cannot be empty.")

            evidence.ghost.isEmpty()  -> throw BadRequestException("This evidence must appear in at least one ghost.")
        }

        existingEvidence.name = evidence.name

        val newGhosts = existingEvidence.ghost
        existingEvidence.ghost.clear()
        existingEvidence.ghost.addAll(newGhosts)

        return evidence
    }

    fun deleteEvidence(id: Long) {
        try {
            evidenceRepository.deleteById(id)
        }
        catch (e: Exception){
            throw NotFoundException("Could not find requested evidence id.")
        }
    }
}