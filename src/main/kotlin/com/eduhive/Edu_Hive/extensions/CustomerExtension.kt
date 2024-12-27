package com.eduhive.Edu_Hive.extensions

import com.eduhive.Edu_Hive.dto.CustomerDTO
import com.eduhive.Edu_Hive.entity.CustomerEntity
import com.eduhive.Edu_Hive.entity.UserEntity

fun CustomerDTO.toCustomerEntity(): CustomerEntity =
    CustomerEntity(
        id = this.id,
        name = this.name,
        email = this.email,
        courses =  mutableListOf()
    )

fun CustomerEntity.toCustomerDto(): CustomerDTO =
    CustomerDTO(
        id = this.id,
        name = this.name,
        email = this.email,
        courses = this.courses.map { it.toCourseDTOWithoutCustomers() }
    )