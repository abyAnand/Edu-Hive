package com.eduhive.Edu_Hive.entity

import com.eduhive.Edu_Hive.enums.Role
import jakarta.persistence.Entity
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "user_entity")
open class UserEntity(
    id: Long? = null,
    name: String,
    email: String,
    password: String,
    role: Role,

) : BaseEntity(id, name, email, password, role)