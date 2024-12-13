package com.example.demo.controller

import com.example.demo.model.User
import com.example.demo.service.TokenService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.naming.AuthenticationException

@RestController
@RequestMapping("/users")
class UserController {

    @Autowired
    private lateinit var authenticationManager: AuthenticationManager
    @Autowired
    private lateinit var tokenService: TokenService

    @PostMapping("/register")
    fun register(
        @RequestBody newUser: User
    ): ResponseEntity<User?>{

        return ResponseEntity(newUser, HttpStatus.CREATED) // Introduce el usuario
    }

    @PostMapping("/login")
    fun login(
        @RequestBody user: User
    ): ResponseEntity<Any>?{

        val authentication: Authentication
        try {
            authentication = authenticationManager.authenticate(UsernamePasswordAuthenticationToken(user.username, user.password))
        } catch (e: AuthenticationException) {
            return ResponseEntity(mapOf("mensaje" to "Credenciales incorrectas dude"), HttpStatus.UNAUTHORIZED)
        }

        var token = ""
        token = tokenService.generarToken(authentication)


        return ResponseEntity(mapOf("token" to token), HttpStatus.CREATED)
    }




}