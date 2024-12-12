package com.example.demo.model

import jakarta.persistence.*

@Entity
@Table(name = "evidences")
data class Evidence(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

    @Column
    val name: String,

    @ManyToMany
    @JoinColumn(name = "id_ghost")
    val ghost: Ghost
)
