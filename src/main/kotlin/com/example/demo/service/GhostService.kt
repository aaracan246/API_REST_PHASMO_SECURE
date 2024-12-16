package com.example.demo.service

import com.example.demo.error.exception.BadRequestException
import com.example.demo.error.exception.NotFoundException
import com.example.demo.model.Ghost
import com.example.demo.repository.GhostRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class GhostService {

    @Autowired
    private lateinit var ghostRepository: GhostRepository

    // Inserta un nuevo fantasma (esto no debería hacer falta salvo que un admin tuviera que usarlo ya que actualmente solo existen 24 fantasmas y ya están todos en la base de datos)
    fun insert(newGhost: Ghost): Ghost {

        when{
            newGhost.name.isBlank() -> throw BadRequestException("Username cannot be empty.")

            newGhost.description.isBlank() -> throw BadRequestException("Password cannot be empty.")

            // Los fantasmas del Phasmophobia tienen 3 pruebas que determinan ante qué fantasma nos encontramos, por lo que hay que controlarlo
            newGhost.evidence.isEmpty() || newGhost.evidence.count() != 3 -> throw BadRequestException("A ghost must have 3 pieces of evidence.")
        }

        ghostRepository.save(newGhost)

        return newGhost
    }


    fun updateGhost(ghost: Ghost): Ghost {

        val existingGhost = ghostRepository.findById(ghost.id!!).orElseThrow{ throw NotFoundException("Could not find requested ghost by id.")}

        when{
            existingGhost.name.isBlank() -> throw BadRequestException("Username cannot be empty.")

            existingGhost.description.isBlank() -> throw BadRequestException("Password cannot be empty.")

            // Los fantasmas del Phasmophobia tienen 3 pruebas que determinan ante qué fantasma nos encontramos, por lo que hay que controlarlo
            existingGhost.evidence.isEmpty() || existingGhost.evidence.count() != 3 -> throw BadRequestException("A ghost must have 3 pieces of evidence.")
        }

        existingGhost.name = ghost.name
        existingGhost.description = ghost.description



        existingGhost.evidence.clear()
        existingGhost.evidence.addAll(ghost.evidence)

        ghostRepository.save(ghost)

        return ghost
    }

    // Borrar un fantasma - esto tampoco debería ser usado ya que no hay un motivo para borrar fantasmas

    fun deleteGhost(id: Long){
        try {
            val ghost = ghostRepository.findById(id).orElseThrow { throw NotFoundException("No ghost was found on that ID.") }
            ghost.id?.let { ghostRepository.deleteById(it) }
        }
        catch (e: Exception) {
            throw NotFoundException("Could not find requested ghost id.")
        }
    }

    // Obtiene todos los fantasmas
    fun getAll(): List<Ghost> = ghostRepository.findAll()

}