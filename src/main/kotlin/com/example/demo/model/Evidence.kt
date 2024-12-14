package com.example.demo.model

import jakarta.persistence.*

@Entity
@Table(name = "evidences")
data class Evidence(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column
    val name: String,

    @ManyToMany(mappedBy = "evidence")
    val ghost: MutableList<Ghost> = mutableListOf()
)
