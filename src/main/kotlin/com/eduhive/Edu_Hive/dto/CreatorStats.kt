package com.eduhive.Edu_Hive.dto

data class CreatorStats(
    val creatorName: String,
    val creatorEmail: String,
    val courses: List<CourseStats>,
    val totalEarnings: Double
)
