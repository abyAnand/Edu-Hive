package com.eduhive.Edu_Hive.annotations.validator

import com.eduhive.Edu_Hive.annotations.ValidRole
import com.eduhive.Edu_Hive.enums.Role
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class RoleValidator : ConstraintValidator<ValidRole, Role?> {
    override fun isValid(value: Role?, context: ConstraintValidatorContext): Boolean {
        return value != null && value in Role.values()
    }
}