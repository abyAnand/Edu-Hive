package com.eduhive.Edu_Hive.dto

data class StatsResponse(
    val creatorStats: List<CreatorStats>,
    val customerStats: List<CustomerStats>
)
