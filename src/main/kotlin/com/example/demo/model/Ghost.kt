package com.example.demo.model

import jakarta.persistence.*

@Entity
@Table(name = "ghosts")
data class Ghost(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column
    val name: String,

    @Column
    val description: String,

    @ManyToMany
    @JoinColumn(name = "id_evidence")
    val evidence: Evidence
)
