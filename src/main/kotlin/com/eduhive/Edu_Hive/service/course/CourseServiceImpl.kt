package com.eduhive.Edu_Hive.service.course

import com.eduhive.Edu_Hive.dto.CourseDTO
import com.eduhive.Edu_Hive.entity.CreatorEntity
import com.eduhive.Edu_Hive.extensions.toCourseDTO
import com.eduhive.Edu_Hive.extensions.toCourseEntity
import com.eduhive.Edu_Hive.repository.CourseRepository
import com.eduhive.Edu_Hive.repository.CreatorRepository
import org.springframework.stereotype.Service

@Service
class CourseServiceImpl(
    private val courseRepository: CourseRepository,
    private val creatorRepository: CreatorRepository
): CourseService {
    override fun save(courseDto: CourseDTO, email : String): CourseDTO {
        val creator: CreatorEntity = creatorRepository.findByEmail(email);
        val couseEntity = courseDto.toCourseEntity(creator);
        val savedCourse = courseRepository.save(couseEntity);
        return savedCourse.toCourseDTO()
    }

    override fun getAllCourses(email: String): List<CourseDTO> {
        val creator: CreatorEntity = creatorRepository.findByEmail(email)
        val courseList = courseRepository.findAllByCreator(creator);
        return courseList.map { it.toCourseDTO() }
    }
}