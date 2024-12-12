package com.example.demo.model

import jakarta.persistence.*

@Entity
@Table(name = "usuarios")
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(unique = true, nullable = false)
    var username: String? = null,

    @Column(nullable = false)
    var password: String? = null,

    var roles: String? = null // e.g., "ROLE_USER,ROLE_ADMIN"

)
