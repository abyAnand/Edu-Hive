package com.eduhive.Edu_Hive.dto

data class CustomerStats(
    val customerName: String,
    val customerEmail: String,
    val courses: List<CourseStats>,
    val totalSpent: Double
)
