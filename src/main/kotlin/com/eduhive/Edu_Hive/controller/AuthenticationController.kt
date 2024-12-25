package com.eduhive.Edu_Hive.controller

import com.eduhive.Edu_Hive.dto.CreateCreatorDto
import com.eduhive.Edu_Hive.dto.CreatorDto
import com.eduhive.Edu_Hive.service.creator.CreatorService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/auth")
class AuthenticationController(private val creatorService: CreatorService) {

    @PostMapping(path = ["/register/creators"])
    fun createCreator(@Valid @RequestBody creator: CreateCreatorDto): ResponseEntity<CreatorDto>{
        println(creator.toString())
        val savedCreator = creatorService.save(creator)
        return ResponseEntity(savedCreator, HttpStatus.CREATED)
    }
}