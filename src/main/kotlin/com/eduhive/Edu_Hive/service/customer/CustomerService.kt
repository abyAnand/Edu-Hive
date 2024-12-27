package com.eduhive.Edu_Hive.service.customer

import com.eduhive.Edu_Hive.dto.CourseDTO
import com.eduhive.Edu_Hive.dto.CustomerDTO
import com.eduhive.Edu_Hive.dto.CustomerStats
import com.eduhive.Edu_Hive.entity.CustomerEntity

interface CustomerService {

    fun create(customerDto: CustomerDTO): CustomerDTO
    fun findByEmail(email: String): CustomerEntity
    fun addCourseToCustomer(customerEmail: String, courseId: Long)
    fun buyCourse(customerEmail: String, courseId: Long)
    fun getCustomerStats(customer: CustomerEntity): CustomerStats
}