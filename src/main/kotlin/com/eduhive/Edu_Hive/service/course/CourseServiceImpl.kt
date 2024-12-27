package com.eduhive.Edu_Hive.service.course

import com.eduhive.Edu_Hive.dto.CourseDTO
import com.eduhive.Edu_Hive.entity.CourseEntity
import com.eduhive.Edu_Hive.entity.CreatorEntity
import com.eduhive.Edu_Hive.extensions.*
import com.eduhive.Edu_Hive.repository.CourseRepository
import com.eduhive.Edu_Hive.repository.CreatorRepository
import com.eduhive.Edu_Hive.repository.CustomerRepository
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalDateTime

@Service
class CourseServiceImpl(
    private val courseRepository: CourseRepository,
    private val creatorRepository: CreatorRepository, private val customerRepository: CustomerRepository
): CourseService {
    override fun save(courseDto: CourseDTO, email : String): CourseDTO {
        val creator: CreatorEntity = creatorRepository.findByEmail(email)
            .orElseThrow { NoSuchElementException("Creator not found with email: $email") }
        val couseEntity = courseDto.toCourseEntity(creator);
        val savedCourse = courseRepository.save(couseEntity);
        return savedCourse.toCourseDTOWithoutCustomers()
    }

    override fun update(course: CourseEntity): CourseDTO {
        val savedCourse = courseRepository.save(course)
        return savedCourse.toCourseDTO()
    }

    override fun getAllCourses(email: String): List<CourseDTO> {
        val creator: CreatorEntity = creatorRepository.findByEmail(email)
            .orElseThrow { NoSuchElementException("Creator not found with email: $email") }
        val courseList = courseRepository.findAllByCreator(creator);
        return courseList.map { it.toCourseDTO() }
    }

    override fun getAllCourses(): List<CourseDTO> {

        val courseList = courseRepository.findAll();
        return courseList.map { it.toCourseDTO() }
    }

    override fun findByCreatorIdAndCreatedDateBetween(creatorId: Long, startDate: LocalDateTime, endDate: LocalDateTime): List<CourseEntity> {
        return courseRepository.findByCreatorIdAndCreatedDateBetween(creatorId, startDate, endDate) // Ensure return type is List<CourseEntity>
    }

    override fun findByCreatorId(creatorId: Long): List<CourseEntity> {
        return courseRepository.findByCreatorId(creatorId)
    }

    override fun buyCourse(email: String, courseId: Long): CourseDTO {
        val customer = customerRepository.findByEmail(email)
        val course = courseRepository.findById(courseId).orElseThrow {
            NoSuchElementException("Course not found")
        }

        // Check if the course is already purchased
        if (customer.courses.contains(course)) {
            throw IllegalArgumentException("Course already purchased")
        }

        // Add the course to the customer's library
        customer.courses.add(course)
        val savedCustomer = customerRepository.save(customer)
        return course.toCourseDTO()
    }

    override fun findCourseById(courseId: Long): CourseDTO {
        val course = courseRepository.findById(courseId);
        if (!course.isPresent){
            throw NoSuchElementException("Course not found")
        }else{
            return course.get().toCourseDto(course.get())
        }
    }

    override fun findCourseEntityById(CourseId: Long): CourseEntity {
        val course = courseRepository.findById(CourseId);
        if (!course.isPresent){
            throw NoSuchElementException("Course not found")
        }else{
            return course.get()
        }
    }

    override fun searchCourse(seachQuery: String): List<CourseDTO> {
        val courseList =  courseRepository.findAllByTitleContainingOrDescriptionContaining(seachQuery, seachQuery)
        return courseList.map { it.toCourseDTO() }
    }
}