package com.eduhive.Edu_Hive.service.userDetails

import com.eduhive.Edu_Hive.entity.UserSecurity
import com.eduhive.Edu_Hive.repository.UserRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.util.Collections

@Service
class UserDetailsService(
    private val userRepository: UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(email: String): UserDetails {
        val user = userRepository.findByEmail(email).orElseThrow{ Exception("User not found")}
        user.id?.let {
            return UserSecurity(
                it,
                user.email,
                user.password,
                Collections.singleton(user.role.getAuthority())
            )
        } ?: run {
            throw Exception("user Not Found")}
    }
}