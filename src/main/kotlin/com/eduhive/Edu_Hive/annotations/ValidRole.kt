package com.eduhive.Edu_Hive.annotations

import com.eduhive.Edu_Hive.annotations.validator.RoleValidator
import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Constraint(validatedBy = [RoleValidator::class])
@Target(AnnotationTarget.FIELD, AnnotationTarget.PROPERTY_GETTER)
@Retention(AnnotationRetention.RUNTIME)
annotation class ValidRole(
    val message: String = "Invalid role. Accepted values are: CREATOR, CUSTOMER, ADMIN",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
