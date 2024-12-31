package com.eduhive.Edu_Hive.repository

import com.eduhive.Edu_Hive.entity.CourseEntity
import com.eduhive.Edu_Hive.entity.CreatorEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate
import java.time.LocalDateTime

@Repository
interface CourseRepository: JpaRepository<CourseEntity, Long> {

    fun findAllByCreator(creator: CreatorEntity): List<CourseEntity>
    fun findByCreatorIdAndCreatedDateBetween(id: Long, startDate: LocalDateTime, endDate: LocalDateTime): List<CourseEntity>

    fun findAllByTitleContainingOrDescriptionContaining(title: String, description: String): List<CourseEntity>
    fun findAllByTitleContainingOrDescriptionContainingIgnoreCase(title: String, description: String): List<CourseEntity>

    fun findByCreatorId(id: Long): List<CourseEntity>


}