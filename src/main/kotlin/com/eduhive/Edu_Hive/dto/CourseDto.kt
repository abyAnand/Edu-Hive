package com.eduhive.Edu_Hive.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size

data class CourseDTO(

    @field:NotNull(message = "ID cannot be null")
    val id: Long?,

    @field:NotBlank(message = "Name cannot be blank")
    @field:Size(max = 200, message = "Name cannot exceed 200 characters")
    val name: String,

    @field:NotNull(message = "Price cannot be null")
    @field:Positive(message = "Price must be a positive value")
    val price: Double,

    @field:NotNull(message = "Creator cannot be null")
    val creator: CreatorDto, // Reference to the Creator's DTO

    @field:NotEmpty(message = "Customers list cannot be empty")
    val customers: List<CustomerDTO> // List of simplified customer DTOs
)