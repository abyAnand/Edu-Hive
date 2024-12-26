package com.eduhive.Edu_Hive.controller



import com.eduhive.Edu_Hive.dto.CreatorDto
import com.eduhive.Edu_Hive.dto.SignUpDto
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

    @PostMapping(path = ["/signup"])
    fun createCreator(@Valid @RequestBody signUpDto: SignUpDto): ResponseEntity<CreatorDto>{
        val savedCreator = creatorService.save(signUpDto)
        return ResponseEntity(savedCreator, HttpStatus.CREATED)
    }
}