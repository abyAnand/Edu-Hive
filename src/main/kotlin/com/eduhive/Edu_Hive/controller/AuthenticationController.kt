package com.eduhive.Edu_Hive.controller



import com.eduhive.Edu_Hive.dto.*
import com.eduhive.Edu_Hive.exceptions.InvalidCredentialsException
import com.eduhive.Edu_Hive.service.creator.CreatorService
import com.eduhive.Edu_Hive.service.user.UserService
import com.eduhive.Edu_Hive.utility.AuthenticationService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/auth")
class AuthenticationController(
    private val userService: UserService<Any>,
    private val authenticationService: AuthenticationService) {

    @PostMapping("signup")
    fun createCreator(@Valid @RequestBody signUpDto: SignUpDto): ResponseEntity<Any> {
        val savedCreator = userService.save(signUpDto)
        return ResponseEntity(savedCreator, HttpStatus.CREATED)
    }

        @PostMapping("/login")
        fun login(@RequestBody authRequest: LoginDto): ResponseEntity<AuthenticationResponse> {
            return try {
                val authResponse = authenticationService.authentication(authRequest)
                ResponseEntity.ok(authResponse)
            } catch (ex: InvalidCredentialsException) {
               throw ex
            }
        }

    @PostMapping("/refresh")
    fun refreshAccessToken(
        @RequestBody request: RefreshTokenRequest
    ): TokenResponse = TokenResponse(token = authenticationService.refreshAccessToken(request.token))
}