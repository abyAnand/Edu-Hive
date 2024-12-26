package com.eduhive.Edu_Hive.entity

import com.eduhive.Edu_Hive.enums.Role
import jakarta.persistence.Entity
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table

@Entity
@Table(name = "customer")
class Customer(
    id: Long?,
    name: String,
    email: String,
    password: String,
    role: Role,


    @ManyToMany(mappedBy = "customers")
    val courses: List<CourseEntity> = emptyList()
) : BaseEntity(id, name, email, password, role)