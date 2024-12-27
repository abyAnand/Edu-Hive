package com.eduhive.Edu_Hive.service.creator


import com.eduhive.Edu_Hive.dto.CreatorDto
import com.eduhive.Edu_Hive.dto.CreatorStats
import com.eduhive.Edu_Hive.dto.SignUpDto
import com.eduhive.Edu_Hive.entity.CreatorEntity
import java.time.LocalDate
import java.time.LocalDateTime

interface CreatorService {

    fun save(creator: CreatorDto): CreatorDto
    fun getAllCreators(): List<CreatorDto>
    fun findByEmail(email: String): CreatorEntity
    fun getCreatorStats(startDate: LocalDateTime? = null, endDate: LocalDateTime? = null): List<CreatorStats>
    fun getCreatorStats(creator: CreatorEntity): CreatorStats
}