package com.eduhive.Edu_Hive.service.user


import com.eduhive.Edu_Hive.dto.SignUpDto
import com.eduhive.Edu_Hive.entity.BaseEntity

interface UserService<T> {
    fun save(signUpDto: SignUpDto): T
}