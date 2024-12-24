package com.eduhive.Edu_Hive.entity

import com.eduhive.Edu_Hive.enums.Role
import jakarta.persistence.Entity
import jakarta.persistence.OneToMany
import jakarta.persistence.Table


@Entity
@Table(name = "creator")
class Creator(
    id: Long?,
    name: String,
    email: String,
    password: String,
    role: Role,


    @OneToMany(mappedBy = "creator")
    val courses: List<Course> = emptyList()
) : BaseEntity(id, name, email, password, role)