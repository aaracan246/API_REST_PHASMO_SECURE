package com.example.demo.error

import jakarta.servlet.http.HttpServletRequest
import org.apache.coyote.BadRequestException
import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class APIExceptionHandler {

    @ExceptionHandler(
        IllegalArgumentException::class,
        NumberFormatException::class,
        BadRequestException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    fun handleBadRequest(request: HttpServletRequest, e: Exception) : ErrorResponse {
        return ErrorResponse(e.message!!, request.requestURI)
    }

    @ExceptionHandler(ChangeSetPersister.NotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    fun handleNotFound(request: HttpServletRequest, e: Exception) : ErrorResponse {
        return ErrorResponse(e.message!!, request.requestURI)
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    fun handleGeneric(request: HttpServletRequest, e: Exception) : ErrorResponse {
        return ErrorResponse(e.message!!, request.requestURI)
    }
}