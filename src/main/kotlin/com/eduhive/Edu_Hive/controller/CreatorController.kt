package com.eduhive.Edu_Hive.controller


import com.eduhive.Edu_Hive.dto.CreateCreatorDto
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/creators")
class CreatorController{

    @PostMapping
    fun createCreator(@Valid @RequestBody creator: CreateCreatorDto){
        println(creator.toString())
    }
}