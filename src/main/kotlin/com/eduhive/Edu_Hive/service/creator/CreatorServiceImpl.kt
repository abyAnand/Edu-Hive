package com.eduhive.Edu_Hive.service.creator


import com.eduhive.Edu_Hive.dto.CreatorDto
import com.eduhive.Edu_Hive.dto.SignUpDto
import com.eduhive.Edu_Hive.entity.CreatorEntity
import com.eduhive.Edu_Hive.extensions.toCreatorDto
import com.eduhive.Edu_Hive.extensions.toCreatorEntity
import com.eduhive.Edu_Hive.extensions.toUserEntity
import com.eduhive.Edu_Hive.repository.CreatorRepository
import org.springframework.stereotype.Service

@Service
class CreatorServiceImpl(
    private val creatorRepository: CreatorRepository
): CreatorService {
    override fun save(creator: CreatorDto): CreatorDto {
        val creatorEntity = creatorRepository.save(creator.toCreatorEntity())
        return creatorEntity.toCreatorDto()
    }

    override fun getAllCreators(): List<CreatorDto> {
        val  creatorList = creatorRepository.findAll();
        return creatorList.map {
            it -> it.toCreatorDto()
        }
    }
}