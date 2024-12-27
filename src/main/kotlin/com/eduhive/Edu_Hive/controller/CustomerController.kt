package com.eduhive.Edu_Hive.controller

import com.eduhive.Edu_Hive.dto.CourseDTO
import com.eduhive.Edu_Hive.dto.CustomerStats
import com.eduhive.Edu_Hive.entity.UserSecurity
import com.eduhive.Edu_Hive.extensions.toCourseDTO
import com.eduhive.Edu_Hive.service.course.CourseService
import com.eduhive.Edu_Hive.service.customer.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
@RequestMapping("/v1/customers")
class CustomerController(
    private val courseService: CourseService,
    private val customerService: CustomerService
) {

    @GetMapping("/course")
    fun getAllCourses(
        @RequestParam(required = false) search: String?,
        @AuthenticationPrincipal user : UserSecurity
    ): ResponseEntity<List<CourseDTO>> {
        val courses = if (search != null) {
            courseService.searchCourse(search)
        } else {
            courseService.getAllCourses()
        }
        return ResponseEntity.ok(courses)
    }

    @GetMapping("/buy/course/{courseId}")
    fun buyCourse(
        @PathVariable courseId: Long,
        @AuthenticationPrincipal user : UserSecurity
    ): ResponseEntity<String> {
        val customerEmail = user.email
        return try {
            courseService.buyCourse(customerEmail, courseId)
            ResponseEntity.ok("Course purchased successfully")
        } catch (e: IllegalArgumentException) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message)
        } catch (e: NoSuchElementException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.message)
        }
    }

    // API 3: Get the customer's purchased courses (library)
    @GetMapping("/library")
    fun getLibrary(@AuthenticationPrincipal user : UserSecurity): ResponseEntity<List<CourseDTO>> {
        val customerEmail = user.email
        val customer = customerService.findByEmail(customerEmail)
        return ResponseEntity.ok(customer.courses.map { it.toCourseDTO() })
    }

    @GetMapping("/stats")
    fun getCustomerStats(@AuthenticationPrincipal user : UserSecurity): CustomerStats {
        var customer = customerService.findByEmail(user.email)
        return customerService.getCustomerStats(customer)
    }
}