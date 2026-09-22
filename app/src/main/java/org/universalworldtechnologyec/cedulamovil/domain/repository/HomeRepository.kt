package org.universalworldtechnologyec.cedulamovil.domain.repository

import org.universalworldtechnologyec.cedulamovil.domain.model.ConsultarRequestDomain
import org.universalworldtechnologyec.cedulamovil.domain.model.ConsultarResponseDomain
import org.universalworldtechnologyec.cedulamovil.domain.model.DetalleRequestDomain
import org.universalworldtechnologyec.cedulamovil.domain.model.DetalleResponseDomain
import org.universalworldtechnologyec.cedulamovil.domain.model.ListarRequestDomain
import org.universalworldtechnologyec.cedulamovil.domain.model.ListarResponseDomain
import org.universalworldtechnologyec.cedulamovil.domain.model.SuscripcionResponseDomain

interface HomeRepository {
    suspend fun suscripcion(): Result<SuscripcionResponseDomain>

    suspend fun listar(data: ListarRequestDomain): Result<ListarResponseDomain>

    suspend fun detalle(data: DetalleRequestDomain): Result<DetalleResponseDomain>

    suspend fun consultar(data: ConsultarRequestDomain): Result<ConsultarResponseDomain>

    suspend fun recientes(): Result<ListarResponseDomain>
}