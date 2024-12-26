package com.eduhive.Edu_Hive.repository

import com.eduhive.Edu_Hive.entity.CourseEntity
import com.eduhive.Edu_Hive.entity.CreatorEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CourseRepository: JpaRepository<CourseEntity, Long> {

    fun findAllByCreator(creator: CreatorEntity): List<CourseEntity>
}