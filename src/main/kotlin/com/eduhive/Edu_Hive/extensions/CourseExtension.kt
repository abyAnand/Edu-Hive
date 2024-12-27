package com.eduhive.Edu_Hive.extensions

import com.eduhive.Edu_Hive.dto.CourseDTO
import com.eduhive.Edu_Hive.entity.CourseEntity
import com.eduhive.Edu_Hive.entity.CreatorEntity
import com.eduhive.Edu_Hive.entity.CustomerEntity

fun CourseDTO.toCourseEntity(creatorEntity: CreatorEntity, customerEntities: List<CustomerEntity>): CourseEntity =
    CourseEntity(
        id = null,
        title = this.title,
        description = this.description,
        price = this.price,
        creator = creatorEntity,
        customers = customerEntities.toMutableList()
    )



fun CourseDTO.toCourseEntity(creatorEntity: CreatorEntity): CourseEntity =
    CourseEntity(
        id = null,
        title = this.title,
        description = this.description,
        price = this.price,
        creator = creatorEntity,
        customers = mutableListOf()
    )

fun CourseDTO.toCourseEntityPopulated(creatorEntity: CreatorEntity): CourseEntity =
    CourseEntity(
        id = this.id,
        title = this.title,
        description = this.description,
        price = this.price,
        creator = creatorEntity,
        customers = this.customers?.map { it.toCustomerEntity() }?.toMutableList() ?: mutableListOf()

    )

fun CourseDTO.toCourseEntity(courseEntity: CourseEntity): CourseEntity =
    CourseEntity(
        id = courseEntity.id,
        title = this.title,
        description = this.description,
        price = this.price,
        creator = courseEntity.creator,
        customers = courseEntity.customers
    )

fun CourseEntity.toCourseDTO(): CourseDTO =
    CourseDTO(
        id = this.id,
        title = this.title,
        description = this.description,
        price = this.price,
        creator = this.creator.toCreatorDtoWithoutCourses(), // Assuming CreatorEntity has a `toCreatorDto` method
        customers = this.customers.toList().map { it.toCustomerDto() }
    )

fun CourseEntity.toCourseDTOWithoutCustomers(): CourseDTO =
    CourseDTO(
        id = this.id,
        title = this.title,
        description = this.description,
        price = this.price,
        creator = this.creator.toCreatorDtoWithoutCourses(), // Assuming CreatorEntity has a `toCreatorDto` method
        customers = emptyList()
    )


fun CourseEntity.toCourseDto(courseEntity: CourseEntity): CourseDTO =
    CourseDTO(
        id = courseEntity.id,
        title = this.title,
        description = this.description,
        price = this.price,
        creator = courseEntity.creator.toCreatorDto(),
        customers = courseEntity.customers.map { it.toCustomerDto() }
    )



