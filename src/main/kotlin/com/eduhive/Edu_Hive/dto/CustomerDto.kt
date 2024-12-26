package com.eduhive.Edu_Hive.dto

import com.eduhive.Edu_Hive.enums.Role
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class CustomerDTO(

    @field:NotNull(message = "ID cannot be null")
    val id: Long?,

    @field:NotBlank(message = "Name cannot be blank")
    @field:Size(max = 100, message = "Name cannot exceed 100 characters")
    val name: String,

    @field:NotBlank(message = "Email cannot be blank")
    @field:Email(message = "Email should be valid")
    val email: String,

//    @field:NotBlank(message = "Password cannot be blank")
//    @field:Size(min = 8, message = "Password must be at least 8 characters long")
//    val password: String,

//    @field:NotNull(message = "Role cannot be null")
//    val role: Role,

    @field:NotEmpty(message = "Courses list cannot be empty")
    val courses: List<CourseDTO> // List of simplified course DTOs
)