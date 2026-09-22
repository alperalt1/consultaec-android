package org.universalworldtechnologyec.cedulamovil.domain.model

data class ForgotPasswordResponseDomain(
   val success: Boolean,
   val message: String,
   val data: String
)


data class ForgotPasswordRequestDomain(
    val email: String
)