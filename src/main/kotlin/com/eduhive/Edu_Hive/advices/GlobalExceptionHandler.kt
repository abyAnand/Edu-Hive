package com.eduhive.Edu_Hive.advices

import com.fasterxml.jackson.databind.exc.InvalidFormatException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleHttpMessageNotReadableException(ex: HttpMessageNotReadableException): ResponseEntity<Map<String, String>> {
        val cause = ex.cause
        val message = if (cause is InvalidFormatException && cause.targetType.isEnum) {
            "Invalid value for enum. Accepted values are: ${(cause.targetType.enumConstants as Array<*>).joinToString(", ")}"
        } else {
            "Invalid input: ${ex.message}"
        }
        val response = mapOf("error" to message)
        return ResponseEntity(response, HttpStatus.BAD_REQUEST)
    }
}