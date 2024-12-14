package com.example.demo.model

import jakarta.persistence.*

@Entity
@Table(name = "users")
data class Usuario(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(unique = true, nullable = false)
    var username: String,

    @Column(nullable = false)
    var password: String,

    @Enumerated(EnumType.STRING) // Para poder almacenarlo como string o peta
    @Column(nullable = false)
    var roles: ROLE? = ROLE.USER
)
