package com.eduhive.Edu_Hive.config


import com.eduhive.Edu_Hive.utility.JWTUtility
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val jwtUtility: JWTUtility,
    private val userDetails: com.eduhive.Edu_Hive.service.userDetails.UserDetailsService,
    private val userDetailsService: UserDetailsService
) {

    private fun authManager(http: HttpSecurity): AuthenticationManager {
        val authenticationManagerBuilder = http.getSharedObject(
            AuthenticationManagerBuilder::class.java
        )
        authenticationManagerBuilder.userDetailsService(userDetailsService)
        return authenticationManagerBuilder.build()
    }

    @Bean
    open fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    open fun filterChain(http: HttpSecurity): SecurityFilterChain {
        val authenticationManager = authManager(http)

        http
            .authenticationManager(authenticationManager)
            .addFilter(JwtAuthenticationFilter(jwtUtility, authenticationManager))
            .addFilter(JwtAuthorizationFilter(jwtUtility, userDetailsService, authenticationManager))
            .sessionManagement { sessionManagement ->
                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .csrf { it.disable() }
            .headers { headers ->
                headers.frameOptions { frameOptions ->
                    frameOptions.disable()
                }
            }
            .authorizeHttpRequests {
                it.requestMatchers("/h2-console/**").permitAll()
            }
            .authorizeHttpRequests {
                auth -> auth.anyRequest().permitAll()
            }


//            .authorizeHttpRequests { auth ->
//                auth.requestMatchers("/v1/auth/signup").permitAll()
//                    .anyRequest().authenticated()
//            }
        return http.build()
    }
}