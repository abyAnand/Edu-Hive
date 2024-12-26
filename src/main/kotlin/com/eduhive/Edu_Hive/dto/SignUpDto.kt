package com.eduhive.Edu_Hive.dto

import com.eduhive.Edu_Hive.annotations.ValidRole
import com.eduhive.Edu_Hive.enums.Role
import jakarta.validation.constraints.*

data class SignUpDto(

//    @field:NotBlank(message = "Name cannot be blank")
//    @field:Size(max = 100, message = "Name cannot exceed 100 characters")
//    val name: String?,

    @field:NotBlank(message = "Email cannot be blank")
    @field:Email(message = "Email should be valid")
    val email: String?,

    @field:NotBlank(message = "Password cannot be blank")
    @field:Size(min = 8, message = "Password must be at least 8 characters long")
    val password: String?,

    @field:NotNull(message = "Role cannot be null")
    @field:ValidRole
    val role: Role?
)