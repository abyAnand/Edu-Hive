package com.eduhive.Edu_Hive.repository

import com.eduhive.Edu_Hive.entity.CreatorEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface CreatorRepository: JpaRepository<CreatorEntity, Long?> {

    fun findByEmail(email: String): Optional<CreatorEntity>
}