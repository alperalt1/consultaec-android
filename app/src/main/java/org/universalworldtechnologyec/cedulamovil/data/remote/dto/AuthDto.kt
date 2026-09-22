package org.universalworldtechnologyec.cedulamovil.data.remote.dto

import kotlinx.serialization.Serializable


@Serializable
data class LoginResponseDto(
    val success: Boolean,
    val message: String,
    val data: DataLoginResponseDto,
    val errors: List<String>? = null,
    val timestamp: String,
)

@Serializable
data class DataLoginResponseDto(
    val userId: Int,
    val fullName: String,
    val email: String,
    val token: String
)

@Serializable
data class LoginRequestDto(
    val email: String,
    val password: String
)


@Serializable
data class RegisterResponseDto(
    val success: Boolean,
    val message: String,
    val data: String,
    val errors: List<String>? = null,
    val timestamp: String,
)

@Serializable
data class RegisterRequestDto(
    val fullName: String,
    val email: String,
    val password: String
)

@Serializable
data class ForgotPasswordResponseDto(
    val success: Boolean,
    val message: String,
    val data: String,
    val errors: List<String>? = null,
    val timestamp: String,
)

@Serializable
data class ForgotPasswordRequestDto(
    val email: String
)

@Serializable
data class ConfirmEmailResponseDto(
    val success: Boolean,
    val message: String,
    val data: String,
    val errors: List<String>? = null,
    val timestamp: String,
)

@Serializable
data class ConfirmEmailRequestDto(
    val email: String,
    val otp: String
)

@Serializable
data class ChangePasswordResponseDto(
    val success: Boolean,
    val message: String,
    val data: String,
    val errors: List<String>? = null,
    val timestamp: String,
)

@Serializable
data class ChangePasswordRequestDto(
    val email: String,
    val otp: String,
    val password: String,
    val confirmPassword: String
)
