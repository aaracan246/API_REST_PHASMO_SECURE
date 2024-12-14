package com.example.demo.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
@Table(name = "evidences")
data class Evidence(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column
    var name: String,

    @ManyToMany(mappedBy = "evidence")
    @JsonIgnore // Con esto evito el bucle infinito
    val ghost: MutableList<Ghost> = mutableListOf()
)
