package com.eduhive.Edu_Hive.config

import com.eduhive.Edu_Hive.dto.LoginDto
import com.eduhive.Edu_Hive.dto.TokenResponse
import com.eduhive.Edu_Hive.entity.UserSecurity
import com.eduhive.Edu_Hive.service.userDetails.UserDetailsService
import com.eduhive.Edu_Hive.utility.JWTUtility
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.GrantedAuthority

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.util.Date

class JwtAuthenticationFilter(
    private val jwtTokenUtil: JWTUtility,

    private val authManager: AuthenticationManager
): UsernamePasswordAuthenticationFilter() {

    @Autowired
    private lateinit var userDetailsService: UserDetailsService

    private fun getAuthoritiesByEmail(email: String): MutableCollection<out GrantedAuthority>? {
        val user = userDetailsService.loadUserByUsername(email);
        return user.authorities;
    }

    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {
        val credentials = ObjectMapper().readValue(request?.inputStream, LoginDto::class.java)
        val authorities = getAuthoritiesByEmail(credentials.email);
        val auth = UsernamePasswordAuthenticationToken(
            credentials.email,
            credentials.password,
            authorities
        )
        return authManager.authenticate(auth)
    }

    override fun successfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        chain: FilterChain?,
        authResult: Authentication?
    ) {
        val userId = (authResult?.principal as UserSecurity).id
        val email = (authResult.principal as UserSecurity).email
        val token: String = jwtTokenUtil.generateToken(userId, email)
        val tokenResponse: TokenResponse = TokenResponse(token)

        val json = Gson().toJson(tokenResponse)
        response?.contentType = "application/json"
        response?.characterEncoding = "UTF-8"
        response?.addHeader("Authorization", token)
        response?.writer?.print(json)
        response?.addHeader("Access-Control-Expose-Headers","Authorization")
    }

    override fun unsuccessfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        failed: AuthenticationException?
    ) {
        val error = InvalidCredentialsError()
        response?.status = error.status
        response?.contentType = "application/json"
        response?.writer?.append(error.toString())
    }

    private data class InvalidCredentialsError(
        val timeStamp: Long = Date().time,
        val status: Int = 401,
        val message: String = "Invalid credentials. Please check your email / password"
    ){
        override fun toString(): String {
            return ObjectMapper().writeValueAsString(this)
        }
    }

}