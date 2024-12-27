package com.eduhive.Edu_Hive.service.customer

import com.eduhive.Edu_Hive.dto.CourseStats
import com.eduhive.Edu_Hive.dto.CustomerDTO
import com.eduhive.Edu_Hive.dto.CustomerStats
import com.eduhive.Edu_Hive.entity.CustomerEntity
import com.eduhive.Edu_Hive.extensions.toCourseEntity
import com.eduhive.Edu_Hive.extensions.toCustomerDto
import com.eduhive.Edu_Hive.extensions.toCustomerEntity
import com.eduhive.Edu_Hive.repository.CustomerRepository
import com.eduhive.Edu_Hive.service.course.CourseService
import org.springframework.stereotype.Service

@Service
class CustomerServiceImpl(
    private val customerRepository: CustomerRepository,

    private val courseService: CourseService
): CustomerService {
    override fun create(customerDto: CustomerDTO): CustomerDTO {
        val CustomerEntity = customerDto.toCustomerEntity()
        val savedCustomerEntity = customerRepository.save(CustomerEntity)
        return savedCustomerEntity.toCustomerDto()
    }

    override fun findByEmail(email: String): CustomerEntity {
        return customerRepository.findByEmail(email)
    }

    override fun addCourseToCustomer(customerEmail: String, courseId: Long) {
        val customer = customerRepository.findByEmail(customerEmail)

        val course = courseService.findCourseEntityById(courseId)

        // Check if the course is already purchased
        if (customer.courses.contains(course)) {
            throw IllegalArgumentException("Customer already owns the course with id: $courseId")
        }

        // Add course to customer's library
        customer.addCourse(course)

        // Save the updated customer (cascade persists relationship to the join table)
        customerRepository.save(customer)
    }

    override fun buyCourse(customerEmail: String, courseId: Long) {
        val customer = customerRepository.findByEmail(customerEmail)
            ?: throw NoSuchElementException("Customer not found with email: $customerEmail")

        val course = courseService.findCourseEntityById(courseId)



        if (customer.courses.contains(course)) {
            throw IllegalArgumentException("Course already purchased")
        }

        customer.addCourse(course)

        // Save changes to the database (only once)
        customerRepository.save(customer)

    }

    override fun getCustomerStats(customer: CustomerEntity): CustomerStats {
        val boughtCourses = customer.courses.filter { it.createdDate != null }

        val coursesStats = boughtCourses.map { course ->
            CourseStats(course.title, 1, course.price) // Each customer buys the course once
        }

        val totalSpent = coursesStats.sumByDouble { it.totalEarnings }

        return CustomerStats(customer.name, customer.email, coursesStats, totalSpent)
    }
}