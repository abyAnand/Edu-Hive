package com.eduhive.Edu_Hive.dto

import java.beans.ConstructorProperties

data class LoginDto
@ConstructorProperties
constructor(
    val email: String,
    val password: String
)