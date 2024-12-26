package com.eduhive.Edu_Hive.controller



import com.eduhive.Edu_Hive.dto.CourseDTO
import com.eduhive.Edu_Hive.dto.CreatorDto
import com.eduhive.Edu_Hive.entity.UserSecurity
import com.eduhive.Edu_Hive.repository.CourseRepository
import com.eduhive.Edu_Hive.service.course.CourseService
import com.eduhive.Edu_Hive.service.creator.CreatorService
import jakarta.validation.Valid
import org.apache.coyote.Response
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/creators")
class CreatorController(
    private val creatorService: CreatorService,
    private val courseService: CourseService){

    @GetMapping
    fun getAllCreators(): ResponseEntity<List<CreatorDto>>{
        val creatorList = creatorService.getAllCreators();
        return ResponseEntity(creatorList, HttpStatus.OK)
    }

    @PostMapping(path = ["/courses"])
    fun createCourse(
        @RequestBody @Valid courseDto: CourseDTO,
        @AuthenticationPrincipal user : UserSecurity): ResponseEntity<CourseDTO>{
        val savedCourse = courseService.save(courseDto,user.email)
        return ResponseEntity(savedCourse, HttpStatus.CREATED)
    }

    @GetMapping(path = ["/courses"])
    fun getCourses(

        @AuthenticationPrincipal user : UserSecurity): ResponseEntity<List<CourseDTO>>{
        val courseList = courseService.getAllCourses(user.email)
        return ResponseEntity(courseList, HttpStatus.OK)
    }
}