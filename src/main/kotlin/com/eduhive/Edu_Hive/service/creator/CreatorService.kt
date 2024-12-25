package com.eduhive.Edu_Hive.service.creator

import com.eduhive.Edu_Hive.dto.CreateCreatorDto
import com.eduhive.Edu_Hive.dto.CreatorDto
import com.eduhive.Edu_Hive.entity.CreatorEntity

interface CreatorService {

    fun save(creator: CreateCreatorDto): CreatorDto
}