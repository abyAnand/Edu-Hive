package com.eduhive.Edu_Hive.enums

import org.springframework.security.core.authority.SimpleGrantedAuthority

enum class Role {
    ADMIN, CREATOR, CUSTOMER;

    fun getAuthority(): SimpleGrantedAuthority {
        return SimpleGrantedAuthority("ROLE_$name")
    }
}
