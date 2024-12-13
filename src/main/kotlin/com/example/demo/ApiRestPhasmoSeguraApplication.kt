package com.example.demo

import com.example.demo.security.RSAKeysProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(RSAKeysProperties::class)
class ApiRestPhasmoSeguraApplication

fun main(args: Array<String>) {
	runApplication<ApiRestPhasmoSeguraApplication>(*args)
}
