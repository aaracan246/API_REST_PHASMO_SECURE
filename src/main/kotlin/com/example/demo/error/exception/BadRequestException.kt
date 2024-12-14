package com.example.demo.error.exception

class BadRequestException(message: String) : RuntimeException("Bad Request Exception (400). $message") {
}