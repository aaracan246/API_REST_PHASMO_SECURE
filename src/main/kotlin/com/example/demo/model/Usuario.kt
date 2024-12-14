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

    @Column(nullable = false)
    var roles: ROLE? = null
)
