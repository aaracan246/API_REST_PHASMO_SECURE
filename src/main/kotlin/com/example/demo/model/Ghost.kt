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
//    @JoinTable(
//        name = "ghost_evidence",
//        joinColumns = [JoinColumn(name = "ghost_id")],
//        inverseJoinColumns = [JoinColumn(name = "evidence_id")]
//    )
    val evidence: List<Evidence> = mutableListOf()
)
