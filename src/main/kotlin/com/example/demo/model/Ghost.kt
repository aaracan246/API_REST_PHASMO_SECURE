package com.example.demo.model

import jakarta.persistence.*

@Entity
@Table(name = "ghosts")
data class Ghost(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

    @Column
    val name: String,

    @Column
    val description: String,


)
