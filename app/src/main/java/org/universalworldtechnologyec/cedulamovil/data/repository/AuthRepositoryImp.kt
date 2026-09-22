package org.universalworldtechnologyec.cedulamovil.data.repository

import androidx.datastore.core.DataStore
import org.universalworldtechnologyec.cedulamovil.data.local.AppConfigDataStore
import org.universalworldtechnologyec.cedulamovil.data.mapper.toData
import org.universalworldtechnologyec.cedulamovil.data.mapper.toDomain
import org.universalworldtechnologyec.cedulamovil.data.remote.AuthApiService
import org.universalworldtechnologyec.cedulamovil.data.remote.dto.UserData
import org.universalworldtechnologyec.cedulamovil.datastore.UserPreferences
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
import org.universalworldtechnologyec.cedulamovil.domain.repository.AuthRepository
import retrofit2.HttpException
import javax.inject.Inject

class AuthRepositoryImp @Inject constructor(
    private val authApiService: AuthApiService,
    private val userPreferencesDataStore: DataStore<UserPreferences>,
    private val appConfigDataStore: AppConfigDataStore
): AuthRepository {

    override suspend fun login(data: LoginRequestDomain): Result<LoginResponseDomain> {
        return runCatching {
            val request = data.toData()

            val response = authApiService.login(request)
            userPreferencesDataStore.updateData {currentPreferences ->
                currentPreferences.toBuilder()
                    .setToken(response.data.token)
                    .build()
            }
            val userData = UserData(
                userId = response.data.userId,
                fullName = response.data.fullName,
                email = response.data.email
            )
            appConfigDataStore.saveUserConfig(userData)
            response.toDomain()
        }.onFailure { error ->
            if (error is HttpException && error.code() == 403) {
                println("ERROR 403 DETECTADO: El usuario no ha validado su correo/cuenta.")
            } else {
                println("ERROR EXACTO EN LOGIN: ${error.localizedMessage}")
            }
            error.printStackTrace()
        }
    }

    override suspend fun register(data: RegisterRequestDomain): Result<RegisterResponseDomain> {
        return runCatching {
            val request = data.toData()
            val response = authApiService.register(request)
            response.toDomain()
        }.onFailure { error ->
            println("ERROR EXACTO EN REGISTER: ${error.localizedMessage}")
            error.printStackTrace()
        }
    }

    override suspend fun forgotpassword(data: ForgotPasswordRequestDomain): Result<ForgotPasswordResponseDomain> {
        return runCatching {
            val request = data.toData()
            val response = authApiService.forgotpassword(request)
            response.toDomain()
        }.onFailure { error ->
            println("ERROR EXACTO EN FORGOTPASSWORD: ${error.localizedMessage}")
            error.printStackTrace()

        }
    }

    override suspend fun validarIdentidad(data: ConfirmEmailRequestDomain): Result<ConfirmEmailResponseDomain> {
        return runCatching {
            val request = data.toData()
            val response = authApiService.confirmEmail(request)
            response.toDomain()
        }.onFailure { error ->
            println("ERROR EXACTO EN CONFIRMEMAIL: ${error.localizedMessage}")
            error.printStackTrace()
        }
    }

    override suspend fun changepassword(data: ChangePasswordRequestDomain): Result<ChangePasswordResponseDomain> {
        return runCatching {
            val request = data.toData()
            val response = authApiService.changepassword(request)
            response.toDomain()
        }.onFailure { error ->
            println("ERROR EXACTO EN CHANGEPASSWORD: ${error.localizedMessage}")
            error.printStackTrace()
        }
    }

    override suspend fun resendotp(data: ForgotPasswordRequestDomain): Result<ForgotPasswordResponseDomain> {
        return runCatching {
            val request = data.toData()
            val response = authApiService.resendotp(request)
            response.toDomain()
        }.onFailure { error ->
            println("ERROR EXACTO EN RESENTOTP: ${error.localizedMessage}")
            error.printStackTrace()
        }
    }


}

