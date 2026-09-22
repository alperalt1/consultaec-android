package org.universalworldtechnologyec.cedulamovil.data.remote

import org.universalworldtechnologyec.cedulamovil.data.remote.dto.ConsultarRequestDto
import org.universalworldtechnologyec.cedulamovil.data.remote.dto.ConsultarResponseDto
import org.universalworldtechnologyec.cedulamovil.data.remote.dto.DetalleRequestDto
import org.universalworldtechnologyec.cedulamovil.data.remote.dto.DetalleResponseDto
import org.universalworldtechnologyec.cedulamovil.data.remote.dto.ListarRequestDto
import org.universalworldtechnologyec.cedulamovil.data.remote.dto.ListarResponseDto
import org.universalworldtechnologyec.cedulamovil.data.remote.dto.SuscripcionResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface HomeApiService {

    @GET(NetworkConstans.HOME_SUSCRIPCION)
    suspend fun suscripcion(): SuscripcionResponseDto

    @POST(NetworkConstans.HOME_LISTAR)
    suspend fun listar(@Body requestDto: ListarRequestDto): ListarResponseDto

    @POST(NetworkConstans.HOME_DETALLE)
    suspend fun detalle(@Body requestDto: DetalleRequestDto): DetalleResponseDto

    @POST(NetworkConstans.HOME_CONSULTAR)
    suspend fun consultar(@Body requestDto: ConsultarRequestDto): ConsultarResponseDto

    @GET(NetworkConstans.HOME_RECIENTES)
    suspend fun recientes(): ListarResponseDto


}