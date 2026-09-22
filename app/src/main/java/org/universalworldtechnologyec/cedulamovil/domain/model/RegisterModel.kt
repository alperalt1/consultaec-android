package org.universalworldtechnologyec.cedulamovil.domain.model

data class RegisterResponseDomain(
    val success: Boolean,
    val message: String,
    val data: String,

)

data class RegisterRequestDomain(
    val fullName: String,
    val email: String,
    val password: String
)