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
        customers = customerEntities
    )

fun CourseDTO.toCourseEntity(creatorEntity: CreatorEntity): CourseEntity =
    CourseEntity(
        id = null,
        title = this.title,
        description = this.description,
        price = this.price,
        creator = creatorEntity,
        customers = emptyList()
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
        creator = this.creator.toCreatorDto(), // Assuming CreatorEntity has a `toCreatorDto` method
        customers = emptyList() // TODO: Map customers properly when the `toCustomerDTO` method is available
    )

