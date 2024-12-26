package com.eduhive.Edu_Hive.entity

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserSecurity(
    val id: Long,
    val email: String,
    private val userPassword: String,
    private val userAuthorities: MutableCollection<GrantedAuthority>
): UserDetails {
    override fun getAuthorities() = userAuthorities
    override fun getPassword() = userPassword
    override fun getUsername() = email
//    override fun isAccountNonLocked() = true
//    override fun isAccountNonExpired() = true
//    override fun isCredentialsNonExpired() = true
//    override fun isEnabled() = true

}