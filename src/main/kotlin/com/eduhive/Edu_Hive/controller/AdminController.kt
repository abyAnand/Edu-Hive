package com.eduhive.Edu_Hive.controller

import com.eduhive.Edu_Hive.dto.CourseStats
import com.eduhive.Edu_Hive.dto.CreatorStats
import com.eduhive.Edu_Hive.entity.UserEntity
import com.eduhive.Edu_Hive.enums.Role
import com.eduhive.Edu_Hive.service.course.CourseService
import com.eduhive.Edu_Hive.service.creator.CreatorService
import com.eduhive.Edu_Hive.service.customer.CustomerService
import com.eduhive.Edu_Hive.service.user.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate
import java.time.LocalDateTime

@RestController
@RequestMapping("/v1/admin")
class AdminController(
    private val userService: UserService<Any>,
    private val courseService: CourseService,
    private val creatorService: CreatorService,
    private val customerService: CustomerService
) {

    @GetMapping(path = ["/user/list"])
    fun getAllUsers(): ResponseEntity<List<UserEntity>> {
        val userList = userService.getAllUsres()
        return ResponseEntity(userList, HttpStatus.OK)
    }

    @GetMapping(path = ["/stats"])
    fun getStats(
        @RequestParam(required = false) startDate: LocalDateTime? = null,
        @RequestParam(required = false) endDate: LocalDateTime? = null
    ): ResponseEntity<List<CreatorStats>> {


        val stats = creatorService.getCreatorStats(startDate, endDate)
        return ResponseEntity.ok(stats)
    }
}