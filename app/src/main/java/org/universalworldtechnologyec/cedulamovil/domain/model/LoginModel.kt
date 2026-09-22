package org.universalworldtechnologyec.cedulamovil.domain.model

data class LoginRequestDomain(
    val email: String,
    val password: String
)

data class LoginResponseDomain(
    val userId: Int,
    val fullName: String,
    val email: String,
    val token: String
)
