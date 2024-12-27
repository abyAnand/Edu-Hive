package com.eduhive.Edu_Hive.extensions

import com.eduhive.Edu_Hive.dto.CreatorDto
import com.eduhive.Edu_Hive.dto.CustomerDTO
import com.eduhive.Edu_Hive.dto.SignUpDto
import com.eduhive.Edu_Hive.entity.CreatorEntity
import com.eduhive.Edu_Hive.entity.CustomerEntity

import com.eduhive.Edu_Hive.entity.UserEntity

fun SignUpDto.toUserEntity() =
    UserEntity(
        name = this.email?.substringBefore('@') ?: throw IllegalArgumentException("Email cannot be null"),
        email = this.email ?: throw IllegalArgumentException("Email cannot be null"),
        password = this.password ?: throw IllegalArgumentException("Password cannot be null"),
        role = this.role ?: throw IllegalArgumentException("Invalid role: $role")
    )


fun UserEntity.toCreatorDto() =
        CreatorDto(
            id = this.id,
            name = this.name,
            email = this.email,
            courses = emptyList()
        )



fun UserEntity.toCustomerDto() =
        CustomerDTO(
            id = this.id,
            name = this.name,
            email = this.email,
            courses = emptyList()
        )

// Extension function to convert UserEntity to CreatorEntity
fun UserEntity.toCreatorEntity(): CreatorEntity =
    CreatorEntity(
        id = this.id,
        name = this.name,
        email = this.email,
        courses = emptyList()
    )

// Extension function to convert UserEntity to CustomerEntity
fun UserEntity.toCustomerEntity(): CustomerEntity =
    CustomerEntity(
        id = this.id,
        name = this.name,
        email = this.email,
        courses =  mutableListOf()
    )