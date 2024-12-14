package com.example.demo.controller

import com.example.demo.error.exception.NotFoundException
import com.example.demo.model.Usuario
import com.example.demo.service.TokenService
import com.example.demo.service.UserService
import org.aspectj.weaver.ast.Not
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*

import javax.naming.AuthenticationException

@RestController
@RequestMapping("/users")
class UserController {

    @Autowired
    private lateinit var userService: UserService
    @Autowired
    private lateinit var authenticationManager: AuthenticationManager
    @Autowired
    private lateinit var tokenService: TokenService


    // "Insertar" un usuario - registrarlo
    @PostMapping("/register")
    fun register(
        @RequestBody newUsuario: Usuario
    ): ResponseEntity<Usuario?>{

        val user = userService.insert(newUsuario)
        return ResponseEntity(newUsuario, HttpStatus.CREATED) // Introduce el usuario
    }

    // Logear
    @PostMapping("/login")
    fun login(
        @RequestBody usuario: Usuario
    ): ResponseEntity<Any>?{

        val authentication: Authentication
        try {
            authentication = authenticationManager.authenticate(UsernamePasswordAuthenticationToken(usuario.username, usuario.password))
        } catch (e: AuthenticationException) {
            return ResponseEntity(mapOf("mensaje" to "There was an error with your credentials."), HttpStatus.UNAUTHORIZED)
        }

        var token = ""
        token = tokenService.generarToken(authentication)


        return ResponseEntity(mapOf("token" to token), HttpStatus.CREATED)
    }


    // Modificar un usuario
    @PutMapping("/{id}")
    fun updateUser(
        @RequestBody usuario: Usuario?
    ): ResponseEntity<Any>?{

        if (usuario != null){
            val updatedUser = userService.updateUser(usuario)
            return ResponseEntity(updatedUser, HttpStatus.OK)
        }
        throw NotFoundException("Could not find the requested user.")
    }

    // Borrar un usuario
    @DeleteMapping("/{id}")
    fun deleteUser(
        @PathVariable id: Long?
    ){
        if (id != null){
            userService.deleteUser(id)
        }
        else{
            throw NotFoundException("Could not find the requested user.")
        }
    }

}