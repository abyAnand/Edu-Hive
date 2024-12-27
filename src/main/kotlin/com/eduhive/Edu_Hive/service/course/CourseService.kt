package com.eduhive.Edu_Hive.service.course

import com.eduhive.Edu_Hive.dto.CourseDTO
import com.eduhive.Edu_Hive.entity.CourseEntity
import java.time.LocalDate
import java.time.LocalDateTime

interface CourseService {
    fun save(courseDto: CourseDTO, email: String): CourseDTO
    fun update(course: CourseEntity): CourseDTO
    fun getAllCourses(email: String): List<CourseDTO>
    fun getAllCourses(): List<CourseDTO>
    fun findByCreatorIdAndCreatedDateBetween(creatorId: Long, startDate: LocalDateTime, endDate: LocalDateTime): List<CourseEntity> // Specify return type
    fun findByCreatorId(creatorId: Long): List<CourseEntity>
    fun buyCourse(email: String, courseId: Long): CourseDTO
    fun findCourseById(courseId: Long): CourseDTO
    fun findCourseEntityById(CourseId: Long): CourseEntity
    fun searchCourse(seachQuery: String): List<CourseDTO>
}