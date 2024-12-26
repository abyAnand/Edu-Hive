package com.eduhive.Edu_Hive.repository

import com.eduhive.Edu_Hive.entity.CustomerEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository: JpaRepository<CustomerEntity, Long> {
}