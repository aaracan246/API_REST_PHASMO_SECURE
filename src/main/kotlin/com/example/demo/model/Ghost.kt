package com.example.demo.model

import com.fasterxml.jackson.annotation.JsonCreator
import jakarta.persistence.*

@Entity
@Table(name = "ghosts")
data class Ghost(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    var name: String = "",

    @Column(nullable = false)
    var description: String = "",

    @ManyToMany
    val evidence: MutableList<Evidence> = mutableListOf()
){
    @JsonCreator // Dejó de funcionar así que tuve que añadir está anotación
    constructor(id: Long?): this(id = id, name = "", description = "") // Constructor secundario o no me deja insertar pruebas (pilla lista vacía)
}
