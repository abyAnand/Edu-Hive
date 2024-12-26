package com.eduhive.Edu_Hive.utility

import io.jsonwebtoken.Claims
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm

import org.springframework.stereotype.Component
import java.util.*


@Component
class JWTUtility(
    private val jwtProperties: JwtProperties
) {

    private val expirationTime = jwtProperties.expiration.toLong()

    fun generateToken(user_id: Long, email: String): String{
        val now = Date()
        val expiration = Date(now.time + expirationTime)
        return Jwts.builder().setSubject(user_id.toString())
            .claim("user_id", user_id)
            .claim("email", email)
            .setExpiration(expiration)
            .signWith(SignatureAlgorithm.HS512, jwtProperties.secretKey.toByteArray()).compact()
    }

    private fun getClaims(token: String): Claims? {
        return try{
            Jwts.parser().setSigningKey(jwtProperties.secretKey.toByteArray()).parseClaimsJws(token).body
        } catch (e : JwtException){
            print(e)
            null
        } catch (e: IllegalArgumentException){
            print(e)
            null
        } catch (e: Exception){
            print(e)
            null
        }
    }

    fun getUserId(token: String): Long = getClaims(token)?.get("user_id") as Long

    fun getUserEmail(token: String): String = getClaims(token)?.get("email") as String

    fun isTokenValid(token: String): Boolean {
        val claims = getClaims(token)
        return if(claims != null){
            val expirationDate = claims.expiration
            val now = Date(System.currentTimeMillis())
            now.before(expirationDate)
        }else {
            return false
        }
    }

}