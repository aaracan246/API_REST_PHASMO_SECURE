package com.example.demo.repository

import com.example.demo.model.Ghost
import org.springframework.data.jpa.repository.JpaRepository

interface GhostRepository: JpaRepository<Ghost, Long>