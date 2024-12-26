package com.eduhive.Edu_Hive.dto

import com.eduhive.Edu_Hive.enums.Role
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size


data class CreatorDto(

    val id: Long?,

    @field:NotBlank(message = "Name cannot be blank")
    @field:Size(max = 100, message = "Name cannot exceed 100 characters")
    val name: String,

    @field:NotBlank(message = "Email cannot be blank")
    @field:Email(message = "Email should be valid")
    val email: String,

    @field:NotNull(message = "Role cannot be null")
    val role: Role,

    val courses: List<CourseDTO>? = null
)