package org.universalworldtechnologyec.cedulamovil.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class SuscripcionResponseDto(
    val success: Boolean,
    val message: String,
    val data: SuscripcionData,
    val errors: List<String>? = null,
    val timestamp: String = ""
)

@Serializable
data class SuscripcionData(
    val suscripcionId: Int = 0,
    val consultasDisponibles: Int = 0,
    val isActive: Boolean = false,
    val fechaInicio: String = "",
    val plan: PlanData
)

@Serializable
data class PlanData(
    val planId: Int = 0,
    val nombre: String = "",
    val precio: Double = 0.0,
    val cantidadConsultasOriginal: Int = 0
)

@Serializable
data class UserData(
    val userId: Int = 0,
    val fullName: String = "",
    val email: String = ""
)

@Serializable
data class ConsultarRequestDto(
    val identificacion: String
)

@Serializable
data class ConsultarResponseDto(
    val success: Boolean,
    val message: String,
    val data: ConsultarData,
    val errors: List<String>? = null,
    val timestamp: String
)

@Serializable
data class ConsultarData(
    val cedula: String = "",
    val genero: String = "",
    val nombre: String = "",
    val conyuge: String = "",
    val success: Boolean = false,
    val profesion: String = "",
    val estadoCivil: String = "",
    val instruccion: String = "",
    val nombreMadre: String = "",
    val nombrePadre: String = "",
    val nacionalidad: String = "",
    val calleDomicilio: String = "",
    val lugarDomicilio: String = "",
    val fechaCedulacion: String = "",
    val fechaNacimiento: String = "",
    val lugarNacimiento: String = "",
    val condicionCedulado: String = "",
    val numeracionDomicilio: String = "",
    val fechaInscripcionGenero: String = "",
    val lugarInscripcionGenero: String = "",
    val fechaInscripcionDefuncion: String = ""
)


@Serializable
data class ListarResponseDto(
    val success: Boolean,
    val message: String,
    val data: List<ListarData>,
    val errors: List<String>? = null,
    val timestamp: String
)

@Serializable
data class ListarData(
    val id: Int,
    val cedulaConsulta: String,
    val nombreCompleto: String,
    val createdOn: String
)

@Serializable
data class ListarRequestDto(
    val cedula: String = ""
)

@Serializable
data class DetalleResponseDto(
    val success: Boolean,
    val message: String,
    val data: DetalleData,
    val errors: List<String>? = null,
    val timestamp: String
)

@Serializable
data class DetalleData(
    val id: Int,
    val cedulaConsulta: String,
    val detalle: DetalleUserData,
)

@Serializable
data class DetalleUserData(
    val cedula: String? = "",
    val genero: String? = "",
    val nombre: String? = "",
    val conyuge: String? = "",
    val success: Boolean = false,
    val profesion: String? = "",
    val estadoCivil: String? = "",
    val instruccion: String? = "",
    val nombreMadre: String? = "",
    val nombrePadre: String? = "",
    val nacionalidad: String? = "",
    val calleDomicilio: String? = "",
    val lugarDomicilio: String? = "",
    val fechaCedulacion: String? = "",
    val fechaNacimiento: String? = "",
    val lugarNacimiento: String? = "",
    val condicionCedulado: String? = "",
    val numeracionDomicilio: String? = "",
    val fechaInscripcionGenero: String? = "",
    val lugarInscripcionGenero: String? = "",
    val fechaInscripcionDefuncion: String? = ""
)

@Serializable
data class DetalleRequestDto(
    val id: String = ""
)