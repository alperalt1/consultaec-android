package org.universalworldtechnologyec.cedulamovil.domain.model


data class ChangePasswordResponseDomain(
    val success: Boolean,
    val message: String,
    val data: String
)

data class ChangePasswordRequestDomain(
    val email: String,
    val otp: String,
    val password: String,
    val confirmPassword: String
)