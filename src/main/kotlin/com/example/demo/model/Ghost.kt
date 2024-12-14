package com.example.demo.model

import jakarta.persistence.*

@Entity
@Table(name = "ghosts")
data class Ghost(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    var name: String,

    @Column(nullable = false)
    var description: String,

    @ManyToMany
    val evidence: MutableList<Evidence> = mutableListOf()
)
