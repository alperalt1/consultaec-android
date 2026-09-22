package org.universalworldtechnologyec.cedulamovil.domain.model

data class ConfirmEmailResponseDomain(
    val success: Boolean,
    val message: String,
    val data: String
)

data class ConfirmEmailRequestDomain(
    val email: String,
    val otp: String
)