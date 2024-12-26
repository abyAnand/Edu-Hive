package com.eduhive.Edu_Hive.service.creator


import com.eduhive.Edu_Hive.dto.CreatorDto
import com.eduhive.Edu_Hive.dto.SignUpDto

interface CreatorService {

    fun save(creator: CreatorDto): CreatorDto
    fun getAllCreators(): List<CreatorDto>
}