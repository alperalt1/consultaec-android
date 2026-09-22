package org.universalworldtechnologyec.cedulamovil.data.repository

import org.universalworldtechnologyec.cedulamovil.data.mapper.toData
import org.universalworldtechnologyec.cedulamovil.data.mapper.toDomain
import org.universalworldtechnologyec.cedulamovil.data.remote.HomeApiService
import org.universalworldtechnologyec.cedulamovil.domain.model.ConsultarRequestDomain
import org.universalworldtechnologyec.cedulamovil.domain.model.ConsultarResponseDomain
import org.universalworldtechnologyec.cedulamovil.domain.model.DetalleRequestDomain
import org.universalworldtechnologyec.cedulamovil.domain.model.DetalleResponseDomain
import org.universalworldtechnologyec.cedulamovil.domain.model.ListarRequestDomain
import org.universalworldtechnologyec.cedulamovil.domain.model.ListarResponseDomain
import org.universalworldtechnologyec.cedulamovil.domain.model.SuscripcionResponseDomain
import org.universalworldtechnologyec.cedulamovil.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImp @Inject constructor(
    private val homeApiService: HomeApiService
): HomeRepository {

    override suspend fun suscripcion(): Result<SuscripcionResponseDomain> {
        return runCatching {
            val response = homeApiService.suscripcion()
            response.toDomain()
        }.onFailure { error ->
            println("ERROR EXACTO EN SUSCRIPCION: ${error.localizedMessage}")
            error.printStackTrace()
        }
    }

    override suspend fun listar(data: ListarRequestDomain): Result<ListarResponseDomain> {
        return runCatching {
            val transform = data.toData()
            val response = homeApiService.listar(transform)
            response.toDomain()
        }.onFailure { error ->
            println("ERROR EXACTO EN LISTAR: ${error.localizedMessage}")
            error.printStackTrace()
        }
    }

    override suspend fun detalle(data: DetalleRequestDomain): Result<DetalleResponseDomain> {
        return runCatching {
            val request = data.toData()
            val response = homeApiService.detalle(request)
            response.toDomain()
        }.onFailure { error ->
            println("ERROR EXACTO EN DETALLE: ${error.localizedMessage}")
            error.printStackTrace()
        }
    }

    override suspend fun consultar(data: ConsultarRequestDomain): Result<ConsultarResponseDomain> {
        return runCatching {
            val request = data.toData()
            val response = homeApiService.consultar(request)
            response.toDomain()
        }.onFailure { error ->
            println("ERROR EXACTO EN CONSULTAR: ${error.localizedMessage}")
            error.printStackTrace()
        }
    }

    override suspend fun recientes(): Result<ListarResponseDomain> {
        return runCatching {
            val response = homeApiService.recientes()
            response.toDomain()
        }.onFailure { error ->
            println("ERROR EXACTO EN RECIENTES: ${error.localizedMessage}")
            error.printStackTrace()
        }
    }
}