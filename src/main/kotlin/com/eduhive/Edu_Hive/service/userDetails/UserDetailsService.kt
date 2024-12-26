package com.eduhive.Edu_Hive.service.userDetails

import com.eduhive.Edu_Hive.entity.UserSecurity
import com.eduhive.Edu_Hive.repository.UserRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.Collections

@Service
class UserDetailsService(
    private val userRepository: UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByEmail(username)
            .orElseThrow { UsernameNotFoundException("User $username not found!") }

        return UserSecurity(
            id = user.id!!,
            email = user.email,
            userPassword = user.password,
            userAuthorities = mutableListOf(user.role.getAuthority())
        )


    }



}