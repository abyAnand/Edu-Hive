package com.eduhive.Edu_Hive.controller



import com.eduhive.Edu_Hive.dto.CreatorDto
import com.eduhive.Edu_Hive.service.creator.CreatorService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/creators")
class CreatorController(private val creatorService: CreatorService){

    @GetMapping
    fun getAllCreators(): ResponseEntity<List<CreatorDto>>{
        val creatorList = creatorService.getAllCreators();
        return ResponseEntity(creatorList, HttpStatus.OK)
    }
}