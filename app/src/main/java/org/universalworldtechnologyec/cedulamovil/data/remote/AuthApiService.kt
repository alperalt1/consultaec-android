package org.universalworldtechnologyec.cedulamovil.data.remote

import org.universalworldtechnologyec.cedulamovil.data.remote.dto.ChangePasswordRequestDto
import org.universalworldtechnologyec.cedulamovil.data.remote.dto.ChangePasswordResponseDto
import org.universalworldtechnologyec.cedulamovil.data.remote.dto.ConfirmEmailRequestDto
import org.universalworldtechnologyec.cedulamovil.data.remote.dto.ConfirmEmailResponseDto
import org.universalworldtechnologyec.cedulamovil.data.remote.dto.ForgotPasswordRequestDto
import org.universalworldtechnologyec.cedulamovil.data.remote.dto.ForgotPasswordResponseDto
import org.universalworldtechnologyec.cedulamovil.data.remote.dto.LoginRequestDto
import org.universalworldtechnologyec.cedulamovil.data.remote.dto.LoginResponseDto
import org.universalworldtechnologyec.cedulamovil.data.remote.dto.RegisterRequestDto
import org.universalworldtechnologyec.cedulamovil.data.remote.dto.RegisterResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {

    @POST(NetworkConstans.AUTH_LOGIN)
    suspend fun login(@Body requestDto: LoginRequestDto): LoginResponseDto

    @POST(NetworkConstans.AUTH_REGISTER)
    suspend fun register(@Body requestDto: RegisterRequestDto): RegisterResponseDto

    @POST(NetworkConstans.AUTH_FORGOTPASSWORD)
    suspend fun forgotpassword(@Body requestDto: ForgotPasswordRequestDto): ForgotPasswordResponseDto

    @POST(NetworkConstans.AUTH_CONFIRMEMAIL)
    suspend fun confirmEmail(@Body requestDto: ConfirmEmailRequestDto): ConfirmEmailResponseDto

    @POST(NetworkConstans.AUTH_CHANGEPASWORD)
    suspend fun changepassword(@Body requestDto: ChangePasswordRequestDto): ChangePasswordResponseDto

    @POST(NetworkConstans.AUTH_REENVIAROTP)
    suspend fun resendotp(@Body requestDto: ForgotPasswordRequestDto): ForgotPasswordResponseDto
}