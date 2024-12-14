package com.example.demo.model

import jakarta.persistence.*

@Entity
@Table(name = "ghosts")
data class Ghost(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false)
    val description: String,

    @ManyToMany
    val evidence: List<Evidence> = mutableListOf()
)
