package com.eduhive.Edu_Hive

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity

@SpringBootApplication
@EnableWebSecurity
class EduHiveApplication

fun main(args: Array<String>) {
	runApplication<EduHiveApplication>(*args)
}
