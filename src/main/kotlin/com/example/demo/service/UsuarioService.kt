package com.example.demo.service

import com.example.demo.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UsuarioService: UserDetailsService {

    @Autowired
    private lateinit var userRepository: UserRepository

    override fun loadUserByUsername(username: String?): UserDetails {

        var usuario = userRepository.findByUsername(username!!).orElseThrow()

        return User.builder()
            .username(usuario.username)
            .password(usuario.password)
            .authorities(usuario.roles)
            .build()
    }
}