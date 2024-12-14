package com.example.demo.repository

import com.example.demo.model.Usuario
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository: JpaRepository<Usuario, Long>{

    fun findByUsername(username: String): Optional<Usuario>
}