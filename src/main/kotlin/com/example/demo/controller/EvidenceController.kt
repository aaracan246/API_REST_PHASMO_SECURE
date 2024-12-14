package com.example.demo.controller

import com.example.demo.error.exception.BadRequestException
import com.example.demo.error.exception.NotFoundException
import com.example.demo.model.Evidence
import com.example.demo.service.EvidenceService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/evidences")
class EvidenceController {

    @Autowired
    private lateinit var evidenceService: EvidenceService

    @GetMapping
    fun getAll(): ResponseEntity<List<Evidence>>{
        val evidences = evidenceService.getAll()

        if (evidences.isNotEmpty()){
            return ResponseEntity(evidences, HttpStatus.OK)
        }
        else{
            throw BadRequestException("The list seems to be empty.")
        }
    }

    @PostMapping("/insert_evidence")
    fun insert(
        @RequestBody newEvidence: Evidence
    ): ResponseEntity<Evidence?>{

        val evidence = evidenceService.insert(newEvidence)

        return ResponseEntity(newEvidence, HttpStatus.CREATED)
    }

    @PutMapping("/update_evidence")
    fun update(
        @RequestBody evidence: Evidence?
    ): ResponseEntity<Evidence>{
        if (evidence != null){
            val updatedEvidence = evidenceService.updateEvidence(evidence)
            return ResponseEntity(updatedEvidence, HttpStatus.OK)
        }
        throw NotFoundException("Could not find requested evidence.")
    }

    @DeleteMapping("/{id}")
    fun delete(
        @PathVariable id: Long?
    ){
        if (id != null) {
            evidenceService.deleteEvidence(id)
        }
        else{
            throw NotFoundException("Could not find requested evidence id.")
        }
    }
}