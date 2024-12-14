package com.example.demo.service


import com.example.demo.error.exception.BadRequestException
import com.example.demo.error.exception.NotFoundException
import com.example.demo.model.ROLE
import com.example.demo.model.Usuario
import com.example.demo.repository.UserRepository
import org.aspectj.weaver.ast.Not
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.User

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService: UserDetailsService {

    @Autowired
    private lateinit var userRepository: UserRepository
    @Autowired
    private lateinit var encoder: PasswordEncoder

    // Cargar usuario via username para SpringBoot
    override fun loadUserByUsername(username: String?): UserDetails {

        val usuario = userRepository.findByUsername(username!!).orElseThrow()
        val roles = usuario.roles?.rolename

        return User.builder()
            .username(usuario.username)
            .password(usuario.password)
            .authorities(roles)
            .build()
    }

    // Insertar usuario - registarlo
    fun insert(userRegister: Usuario): Usuario {

        when{
            userRegister.username.isBlank() -> throw BadRequestException("Username cannot be empty.")

            userRegister.password.isBlank() -> throw BadRequestException("Password cannot be empty.")

            userRegister.roles != ROLE.ADMIN && userRegister.roles != ROLE.USER -> throw BadRequestException("A role must be assigned.")
        }

        userRegister.password = encoder.encode(userRegister.password)
        userRepository.save(userRegister)

        return userRegister
    }

    // Updatear usuario
    fun updateUser(usuario: Usuario): Usuario {

        val existingUser = userRepository.findById(usuario.id!!).orElseThrow { NotFoundException("No user was found on that id.")  }

        when{
            existingUser.username.isBlank() -> throw BadRequestException("Username cannot be empty.")

            existingUser.password.isBlank() -> throw BadRequestException("Password cannot be empty.")

            existingUser.roles != ROLE.ADMIN && existingUser.roles != ROLE.USER -> throw BadRequestException("A role must be assigned.")
        }
        existingUser.username = usuario.username
        existingUser.password = encoder.encode(usuario.password)
        existingUser.roles = usuario.roles

        userRepository.save(usuario)


        return usuario
    }

    // Borrar usuario
    fun deleteUser(id: Long) {
        try {
            userRepository.deleteById(id)
        }
        catch (e: Exception){
            throw NotFoundException("Could not find requested user.")
        }
    }
}