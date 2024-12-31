package com.eduhive.Edu_Hive.config


import com.eduhive.Edu_Hive.repository.UserRepository
import com.eduhive.Edu_Hive.service.userDetails.UserDetailsService
import org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
class SecurityConfig {
    @Bean
    fun userDetailsService(userRepository: UserRepository): UserDetailsService =
        UserDetailsService(userRepository)

    @Bean
    fun authenticationManager(config: AuthenticationConfiguration): AuthenticationManager =
        config.authenticationManager

    @Bean
    fun authenticationProvider(userRepository: UserRepository): AuthenticationProvider =
        DaoAuthenticationProvider().apply {
            setUserDetailsService(userDetailsService(userRepository))
            setPasswordEncoder(passwordEncoder())
        }

    @Bean
    fun securityFilterChain(
        http: HttpSecurity,
        jwtAuthenticationFilter: JwtAuthorizationFilter,
        authenticationProvider: AuthenticationProvider
    ): DefaultSecurityFilterChain {
        http
            .csrf { csrf ->
                csrf.ignoringRequestMatchers(toH2Console()) // Disable CSRF for H2 Console
                    .disable()
            }
            .authorizeHttpRequests { auth ->
                auth
                    .requestMatchers(
                        "/v1/auth/signup", // Permit access to /v1/auth/signup
                        "/v1/auth/**", // Permit other /v1/auth endpoints if necessary
                        "/h2-console/**" // Permit access to H2 console
                    ).permitAll()
                    .anyRequest().authenticated() // Secure all other endpoints
            }
            .exceptionHandling { handling ->
                handling
                    .authenticationEntryPoint { _, response, exception ->
                        response.contentType = "application/json"
                        response.characterEncoding = "UTF-8"

                        when (response.status) {
                            HttpStatus.CONFLICT.value() -> {
                                response.status = HttpStatus.CONFLICT.value()
                                val errorMessage = """{ "error": "Resource already exists in the database" }"""
                                response.writer.write(errorMessage)
                            }
                            else -> {
                                response.status = HttpStatus.UNAUTHORIZED.value() // Default to 401 if not explicitly set
                                val errorMessage = """{ "error": "${exception.message ?: "Unauthorized access"}" }"""
                                response.writer.write(errorMessage)
                            }
                        }
                    }
                    .accessDeniedHandler { _, response, _ ->
                        response.status = HttpStatus.FORBIDDEN.value()
                        response.contentType = "application/json"
                        response.characterEncoding = "UTF-8"
                        val errorMessage = """{ "error": "Forbidden" }"""
                        response.writer.write(errorMessage)
                    }
            }
            .sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Stateless sessions for JWT
            }
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
            .headers { headers ->
                headers.frameOptions().sameOrigin() // Allow H2 console
            }
        return http.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()
}
