package org.universalworldtechnologyec.cedulamovil.domain.repository


import org.universalworldtechnologyec.cedulamovil.domain.model.ChangePasswordRequestDomain
import org.universalworldtechnologyec.cedulamovil.domain.model.ChangePasswordResponseDomain
import org.universalworldtechnologyec.cedulamovil.domain.model.ConfirmEmailRequestDomain
import org.universalworldtechnologyec.cedulamovil.domain.model.ConfirmEmailResponseDomain
import org.universalworldtechnologyec.cedulamovil.domain.model.ForgotPasswordRequestDomain
import org.universalworldtechnologyec.cedulamovil.domain.model.ForgotPasswordResponseDomain
import org.universalworldtechnologyec.cedulamovil.domain.model.LoginRequestDomain
import org.universalworldtechnologyec.cedulamovil.domain.model.LoginResponseDomain
import org.universalworldtechnologyec.cedulamovil.domain.model.RegisterRequestDomain
import org.universalworldtechnologyec.cedulamovil.domain.model.RegisterResponseDomain

interface AuthRepository {
    suspend fun login(data: LoginRequestDomain): Result<LoginResponseDomain>
    suspend fun register(data: RegisterRequestDomain): Result<RegisterResponseDomain>
    suspend fun forgotpassword(data: ForgotPasswordRequestDomain): Result<ForgotPasswordResponseDomain>
    suspend fun validarIdentidad(data: ConfirmEmailRequestDomain): Result<ConfirmEmailResponseDomain>
    suspend fun changepassword(data: ChangePasswordRequestDomain): Result<ChangePasswordResponseDomain>
    suspend fun resendotp(data: ForgotPasswordRequestDomain): Result<ForgotPasswordResponseDomain>
}