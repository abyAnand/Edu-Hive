package com.eduhive.Edu_Hive.service.creator


import com.eduhive.Edu_Hive.dto.*
import com.eduhive.Edu_Hive.entity.CreatorEntity
import com.eduhive.Edu_Hive.enums.Role
import com.eduhive.Edu_Hive.extensions.toCreatorDto
import com.eduhive.Edu_Hive.extensions.toCreatorEntity
import com.eduhive.Edu_Hive.extensions.toUserEntity
import com.eduhive.Edu_Hive.repository.CourseRepository
import com.eduhive.Edu_Hive.repository.CreatorRepository
import com.eduhive.Edu_Hive.service.course.CourseService
import com.eduhive.Edu_Hive.service.customer.CustomerService
import com.eduhive.Edu_Hive.service.user.UserService
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalDateTime

@Service
class CreatorServiceImpl(
    private val creatorRepository: CreatorRepository,
    private val courseService: CourseService,
    private val customerService: CustomerService,
    private val userService: UserService<Any>,
    private val courseRepository: CourseRepository
): CreatorService {
    override fun save(creator: CreatorDto): CreatorDto {
        val creatorEntity = creatorRepository.save(creator.toCreatorEntity())
        return creatorEntity.toCreatorDto()
    }

    override fun getAllCreators(): List<CreatorDto> {
        val  creatorList = creatorRepository.findAll();
        return creatorList.map {
            it -> it.toCreatorDto()
        }
    }

    override fun findByEmail(email: String): CreatorEntity {
        return creatorRepository.findByEmail(email)
            .orElseThrow { NoSuchElementException("Creator not found with email: $email") }
    }

    override fun getCreatorStats(startDate: LocalDateTime?, endDate: LocalDateTime?): List<CreatorStats> {
        val users = userService.getAllUsres()

        // Separate creators by role
        val creators = users.filter { it.role == Role.CREATOR }

        return creators.map { user ->
            // Fetch Creator entity by email
            val creator = this.findByEmail(user.email)

            // Fetch courses based on startDate and endDate
            val courses = if (startDate != null && endDate != null) {
                courseService.findByCreatorIdAndCreatedDateBetween(creator.id!!, startDate, endDate)
            } else {
                courseService.findByCreatorId(creator.id!!)
            }

            val coursesStats = courses.map { course ->
                val numberOfCustomers = course.customers.size
                val totalEarnings = course.customers.sumByDouble { course.price }
                CourseStats(course.title, numberOfCustomers, totalEarnings)
            }

            CreatorStats(creator.name, creator.email, coursesStats, coursesStats.sumByDouble { it.totalEarnings })
        }
    }

    override fun getCreatorStats(creator: CreatorEntity): CreatorStats {
        // Fetch courses for the creator
        val courses = creator.id?.let { courseService.findByCreatorId(it) } ?: emptyList()

        // Map courses to CourseStats and handle potential nullability
        val coursesStats = courses.map { course ->
            val numberOfCustomers = course.customers.size
            val totalEarnings = course.customers.sumByDouble { course.price }
            CourseStats(course.title, numberOfCustomers, totalEarnings)
        }

        // Calculate total earnings, default to 0 if no courses
        val totalEarnings = coursesStats.sumByDouble { it.totalEarnings }

        return CreatorStats(creator.name, creator.email, coursesStats, totalEarnings)
    }

    fun getCustomerStats(): List<CustomerStats> {
        val users = userService.getAllUsres()

        // Separate customers by role
        val customers = users.filter { it.role == Role.CUSTOMER }

        return customers.map { user ->
            // Fetch Customer entity by email
            val customer = customerService.findByEmail(user.email)

            val boughtCourses = customer.courses.filter { it.createdDate != null } // Filter out empty or uncreated courses
            val coursesStats = boughtCourses.map { course ->
                CourseStats(course.title, 1, course.price) // Each customer buys the course once
            }
            val totalSpent = coursesStats.sumByDouble { it.totalEarnings }

            CustomerStats(customer.name, customer.email, coursesStats, totalSpent)
        }
    }

    fun getStats(startDate: LocalDateTime?, endDate: LocalDateTime?): StatsResponse {
        val creatorStats = getCreatorStats(startDate, endDate)
        val customerStats = getCustomerStats()

        return StatsResponse(creatorStats, customerStats)
    }
}