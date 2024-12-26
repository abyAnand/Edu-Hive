package com.eduhive.Edu_Hive.utility

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "application.security.jwt")
data class JwtProperties(
    var secretKey: String = "",
    var expiration: String = "",
    var refreshToken: RefreshToken = RefreshToken()
) {
    data class RefreshToken(
        var expiration: String = ""
    )
}