package com.example.demo.controller

import com.example.demo.error.exception.BadRequestException
import com.example.demo.error.exception.NotFoundException
import com.example.demo.model.Ghost
import com.example.demo.service.GhostService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/ghosts")
class GhostController {

    @Autowired
    private lateinit var ghostService: GhostService

    // Obtiene todos los fantasmas
    @GetMapping
    fun getAll(): ResponseEntity<List<Ghost>>{

        val ghosts = ghostService.getAll()

        if (ghosts.isNotEmpty()) {
            return ResponseEntity(ghosts, HttpStatus.OK)
        }
        else{
            throw BadRequestException("The list seems to be empty.")
        }
    }

    // Inserta un fantasma nuevo
    @PostMapping("/insert_ghost")
    fun insert(
        @RequestBody newGhost: Ghost
    ): ResponseEntity<Ghost?>{

        val ghost = ghostService.insert(newGhost)
        return ResponseEntity(newGhost, HttpStatus.CREATED)
    }

    // Updatea el fantasma - Esta función sí es viable ya que los fantasmas sufren balanceo de vez en cuando y podrían llegar a cambiar de pruebas
    @PutMapping("/update_ghost")
    fun updateGhost(
        @RequestBody ghost: Ghost?
    ): ResponseEntity<Any>?{

        if (ghost != null){
            val updatedGhost = ghostService.updateGhost(ghost)
            return ResponseEntity(updatedGhost, HttpStatus.OK)
        }
        throw NotFoundException("Could not find the requested ghost.")
    }

    // Borra un fantasma vía id
    @DeleteMapping("/delete_ghost")
    fun deleteGhost(
        @PathVariable id: Long?
    ){
        if (id != null){
            ghostService.deleteGhost(id)
        }
        else{
            throw NotFoundException("Could not find requested ghost id")
        }
    }
}