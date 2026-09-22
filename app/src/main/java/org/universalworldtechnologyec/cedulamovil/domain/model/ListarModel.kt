package org.universalworldtechnologyec.cedulamovil.domain.model

data class ListarResponseDomain(
    val success: Boolean = true,
    val message: String = "",
    val data: List<ListarConsultaDomain> = emptyList()
)

data class ListarConsultaDomain(
    val id: Int,
    val cedulaConsulta: String,
    val nombreCompleto: String,
    val createdOn: String
)

data class ListarRequestDomain(
    val cedula: String = ""
)