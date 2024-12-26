package com.eduhive.Edu_Hive.service.course

import com.eduhive.Edu_Hive.dto.CourseDTO

interface CourseService {

    fun save(courseDto: CourseDTO, email: String) : CourseDTO
    fun getAllCourses(email: String): List<CourseDTO>
}