package com.eduhive.Edu_Hive.entity

import com.eduhive.Edu_Hive.enums.Role
import jakarta.persistence.Entity
import jakarta.persistence.OneToMany
import jakarta.persistence.Table


@Entity
@Table(name = "creator")
open class CreatorEntity(
    id: Long? = null,
    name: String,
    email: String,
    password: String,
    role: Role,


    @OneToMany(mappedBy = "creator")
    val courses: List<CourseEntity> = emptyList()
) : BaseEntity(id, name, email, password, role)