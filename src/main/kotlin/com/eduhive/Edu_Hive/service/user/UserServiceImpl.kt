package com.eduhive.Edu_Hive.service.user

import com.eduhive.Edu_Hive.dto.SignUpDto
import com.eduhive.Edu_Hive.entity.BaseEntity
import com.eduhive.Edu_Hive.entity.CreatorEntity
import com.eduhive.Edu_Hive.enums.Role
import com.eduhive.Edu_Hive.extensions.*
import com.eduhive.Edu_Hive.repository.CreatorRepository
import com.eduhive.Edu_Hive.repository.CustomerRepository
import com.eduhive.Edu_Hive.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val creatorRepository: CreatorRepository,
    private val customerRepository: CustomerRepository
) : UserService<Any> {
    override fun save(signUpDto: SignUpDto): Any {

        val email = signUpDto.email ?: throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is required")

        // Check if a user with the given email already exists
        val existingUser = userRepository.findByEmail(email)
        if (!existingUser.isEmpty || existingUser.isPresent) {
            // If user exists, throw a conflict error
            throw ResponseStatusException(HttpStatus.CONFLICT, "Email already exists")
        }

        var userEntity = signUpDto.toUserEntity()
        userEntity.password = passwordEncoder.encode(userEntity.password)



        val savedUserEntity = userRepository.save(userEntity)

        return when(savedUserEntity.role){
            Role.CREATOR -> {
                val creatorEntity = savedUserEntity.toCreatorEntity()
                creatorRepository.save(creatorEntity)
            }
            Role.CUSTOMER -> {
                val customerEntity = savedUserEntity.toCustomerEntity()
                customerRepository.save(customerEntity)
            }

            Role.ADMIN -> {
                return savedUserEntity
            }
        }

//        return when (savedUserEntity.role) {
//            Role.CREATOR -> savedUserEntity.toCreatorDto() ?: throw IllegalArgumentException("Invalid creator entity")
//            Role.CUSTOMER -> savedUserEntity.toCustomerDto() ?: throw IllegalArgumentException("Invalid customer entity")
//            else -> throw IllegalArgumentException("Unsupported role: ${savedUserEntity.role}")
//        }

    }
}