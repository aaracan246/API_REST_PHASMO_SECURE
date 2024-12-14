package com.example.demo.service

import com.example.demo.error.exception.BadRequestException
import com.example.demo.error.exception.NotFoundException
import com.example.demo.model.Evidence
import com.example.demo.repository.EvidenceRepository
import com.example.demo.repository.GhostRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class EvidenceService {

    @Autowired
    private lateinit var evidenceRepository: EvidenceRepository

    @Autowired
    private lateinit var  ghostRepository: GhostRepository

    fun getAll(): List<Evidence> = evidenceRepository.findAll()

    fun insert(newEvidence: Evidence): Evidence {

        when{
            newEvidence.name.isBlank() -> throw BadRequestException("Username cannot be empty.")

            newEvidence.ghost.isEmpty()  -> throw BadRequestException("This evidence must appear in at least one ghost.")
        }

        // Buscar fantasmas para asociarlos en la tabla cruzada
        val existingGhosts = newEvidence.ghost.mapNotNull {
            ghostRepository.findById(it.id!!).orElseThrow{ throw NotFoundException("Could not find requested ghost by that id.") }
        }



        newEvidence.ghost.clear()
        newEvidence.ghost.addAll(existingGhosts)

        return evidenceRepository.save(newEvidence)
    }

    fun updateEvidence(evidence: Evidence): Evidence {

        val existingEvidence = evidenceRepository.findById(evidence.id!!).orElseThrow{ throw NotFoundException("Could not find requested evidence by id.")}

        when{
            evidence.name.isBlank() -> throw BadRequestException("Username cannot be empty.")

            evidence.ghost.isEmpty()  -> throw BadRequestException("This evidence must appear in at least one ghost.")
        }

        existingEvidence.name = evidence.name


        existingEvidence.ghost.clear()
        existingEvidence.ghost.addAll(evidence.ghost)

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