package com.eduhive.Edu_Hive.extensions


import com.eduhive.Edu_Hive.dto.CreatorDto

import com.eduhive.Edu_Hive.dto.SignUpDto
import com.eduhive.Edu_Hive.entity.CreatorEntity
import com.eduhive.Edu_Hive.entity.UserEntity
import org.hibernate.Hibernate

fun CreatorDto.toCreatorEntity(creator: CreatorEntity) =
    CreatorEntity(
        id = this.id,
        name = this.name,
        email = this.email,
//        role = this.role,
//        password = creator.password,
        courses = creator.courses
    )

fun CreatorDto.toCreatorEntity() =
    CreatorEntity(
        id = this.id,
        name = this.name,
        email = this.email,
//        role = this.role,
//        password = creator.password,
        courses = emptyList()
    )

//fun SignUpDto.toUserEntity() =
//    UserEntity(
//        name = this.email?.substringBefore('@') ?: throw IllegalArgumentException("Email cannot be null"),
//        email = this.email ?: throw IllegalArgumentException("Email cannot be null"),
//        password = this.password ?: throw IllegalArgumentException("Password cannot be null"),
//        role = this.role ?: throw IllegalArgumentException("Invalid role: $role")
//
//)


fun CreatorEntity.toCreatorDto(): CreatorDto {

    return CreatorDto(
        id = this.id,
        name = this.name,
        email = this.email,
        courses = this.courses.map { it.toCourseDTO() } // Now this will not trigger lazy loading errors
    )
}

fun CreatorEntity.toCreatorDtoWithoutCourses(): CreatorDto {

    return CreatorDto(
        id = this.id,
        name = this.name,
        email = this.email,
        courses = mutableListOf()
    )
}
