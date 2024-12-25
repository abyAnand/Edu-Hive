package com.eduhive.Edu_Hive.service.creator

import com.eduhive.Edu_Hive.dto.CreateCreatorDto
import com.eduhive.Edu_Hive.dto.CreatorDto
import com.eduhive.Edu_Hive.entity.CreatorEntity
import com.eduhive.Edu_Hive.extensions.toCreatorDto
import com.eduhive.Edu_Hive.extensions.toCreatorEntity
import com.eduhive.Edu_Hive.repository.CreatorRepository
import org.springframework.stereotype.Service

@Service
class CreatorServiceImpl(private val creatorRepository: CreatorRepository): CreatorService {
    override fun save(creator: CreateCreatorDto): CreatorDto {
        val creatorEntity = creatorRepository.save(creator.toCreatorEntity())
        return creatorEntity.toCreatorDto()
    }
}