package com.eduhive.Edu_Hive.utility

import com.eduhive.Edu_Hive.dto.AuthenticationResponse
import com.eduhive.Edu_Hive.dto.LoginDto
import com.eduhive.Edu_Hive.entity.UserEntity
import com.eduhive.Edu_Hive.repository.RefreshTokenRepository
import com.eduhive.Edu_Hive.service.userDetails.UserDetailsService
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationServiceException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails

import org.springframework.stereotype.Service
import java.util.Date

@Service
class AuthenticationService(
    private val authManager: AuthenticationManager,
    private val userDetailsService: UserDetailsService,
    private val tokenService: TokenService,
    private val refreshTokenRepository: RefreshTokenRepository,
    @Value("\${application.security.jwt.expiration}") private val accessTokenExpiration: Long = 0,
    @Value("\${application.security.jwt.refresh-token.expiration}") private val refreshTokenExpiration: Long = 0
) {
    fun authentication(authenticationRequest: LoginDto): AuthenticationResponse {
        authManager.authenticate(
            UsernamePasswordAuthenticationToken(
                authenticationRequest.email,
                authenticationRequest.password
            )
        )

        val user = userDetailsService.loadUserByUsername(authenticationRequest.email)

        val accessToken = createAccessToken(user)
        val refreshToken = createRefreshToken(user)

        refreshTokenRepository.save(refreshToken, user)

        return AuthenticationResponse(
            accessToken = accessToken,
            refreshToken = refreshToken
        )
    }

    fun refreshAccessToken(refreshToken: String): String {
        val username = tokenService.extractUsername(refreshToken)

        return username.let { user ->
            val currentUserDetails = userDetailsService.loadUserByUsername(user)
            val refreshTokenUserDetails = refreshTokenRepository.findUserDetailsByToken(refreshToken)

            if (currentUserDetails.username == refreshTokenUserDetails?.username)
                createAccessToken(currentUserDetails)
            else
                throw AuthenticationServiceException("Invalid refresh token")
        }
    }

    private fun createAccessToken(user: UserDetails): String {
        val claims = mutableMapOf<String, Any>()
        claims["authorities"] = user.authorities
        return tokenService.generateToken(
            subject = user.username,
            expiration = Date(System.currentTimeMillis() + accessTokenExpiration),
            additionalClaims = claims

        )
    }

    private fun createRefreshToken(user: UserDetails) : String {
        val claims = mutableMapOf<String, Any>()
        claims["authorities"] = user.authorities
        return tokenService.generateToken(
            subject = user.username,
            expiration = Date(System.currentTimeMillis() + refreshTokenExpiration),
            additionalClaims = claims
        )

    }
}