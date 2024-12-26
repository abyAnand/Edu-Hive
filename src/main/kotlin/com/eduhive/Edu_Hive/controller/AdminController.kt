package com.eduhive.Edu_Hive.controller

import com.eduhive.Edu_Hive.entity.UserEntity
import com.eduhive.Edu_Hive.service.user.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/admin")
class AdminController(
    private val userService: UserService<Any>
) {

    @GetMapping(path = ["/user/list"])
    fun getAllUsers(): ResponseEntity<List<UserEntity>>{
        val userList = userService.getAllUsres()
        return ResponseEntity(userList, HttpStatus.OK)
    }
}