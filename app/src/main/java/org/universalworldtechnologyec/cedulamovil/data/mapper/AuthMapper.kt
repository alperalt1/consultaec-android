package org.universalworldtechnologyec.cedulamovil.data.mapper

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


fun LoginResponseDto.toDomain(): LoginResponseDomain = LoginResponseDomain(
    userId = data.userId,
    fullName = data.fullName,
    email = data.email,
    token = data.token
)

fun LoginRequestDomain.toData(): LoginRequestDto = LoginRequestDto(
    email = email,
    password = password
)

fun RegisterResponseDto.toDomain(): RegisterResponseDomain = RegisterResponseDomain(
    success = success,
    data = data,
    message = message
)

fun RegisterRequestDomain.toData(): RegisterRequestDto = RegisterRequestDto(
    fullName = fullName,
    email = email,
    password = password
)

fun ForgotPasswordResponseDto.toDomain(): ForgotPasswordResponseDomain = ForgotPasswordResponseDomain(
    success = success,
    data = data,
    message = message
)

fun ForgotPasswordRequestDomain.toData(): ForgotPasswordRequestDto = ForgotPasswordRequestDto(
    email = email
)

fun ConfirmEmailRequestDomain.toData(): ConfirmEmailRequestDto = ConfirmEmailRequestDto(
    email = email,
    otp = otp
)

fun ConfirmEmailResponseDto.toDomain(): ConfirmEmailResponseDomain = ConfirmEmailResponseDomain(
    success = success,
    message = message,
    data = data
)

fun ChangePasswordRequestDomain.toData(): ChangePasswordRequestDto = ChangePasswordRequestDto(
    email = email,
    otp = otp,
    password = password,
    confirmPassword = confirmPassword
)

fun ChangePasswordResponseDto.toDomain(): ChangePasswordResponseDomain = ChangePasswordResponseDomain(
    success = success,
    message = message,
    data = data
)