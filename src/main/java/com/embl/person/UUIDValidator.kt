//package com.embl.person
//
//import java.util.UUID
//import javax.validation.ConstraintValidator
//import javax.validation.ConstraintValidatorContext
//
//class UUIDValidator : ConstraintValidator<GUID, String> {
//    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
//        try {
//            UUID.fromString(value)
//        } catch (ex: Exception) {
//            return false
//        }
//        return true
//    }
//}

