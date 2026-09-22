package org.universalworldtechnologyec.cedulamovil.data.mapper

import org.universalworldtechnologyec.cedulamovil.data.remote.dto.ConsultarRequestDto
import org.universalworldtechnologyec.cedulamovil.data.remote.dto.ConsultarResponseDto
import org.universalworldtechnologyec.cedulamovil.data.remote.dto.DetalleRequestDto
import org.universalworldtechnologyec.cedulamovil.data.remote.dto.DetalleResponseDto
import org.universalworldtechnologyec.cedulamovil.data.remote.dto.ListarData
import org.universalworldtechnologyec.cedulamovil.data.remote.dto.ListarRequestDto
import org.universalworldtechnologyec.cedulamovil.data.remote.dto.ListarResponseDto
import org.universalworldtechnologyec.cedulamovil.data.remote.dto.PlanData
import org.universalworldtechnologyec.cedulamovil.data.remote.dto.SuscripcionResponseDto
import org.universalworldtechnologyec.cedulamovil.domain.model.ConsultarRequestDomain
import org.universalworldtechnologyec.cedulamovil.domain.model.ConsultarResponseDomain
import org.universalworldtechnologyec.cedulamovil.domain.model.DetalleRequestDomain
import org.universalworldtechnologyec.cedulamovil.domain.model.DetalleResponseDomain
import org.universalworldtechnologyec.cedulamovil.domain.model.ListarConsultaDomain
import org.universalworldtechnologyec.cedulamovil.domain.model.ListarRequestDomain
import org.universalworldtechnologyec.cedulamovil.domain.model.ListarResponseDomain
import org.universalworldtechnologyec.cedulamovil.domain.model.PlanDataDomain
import org.universalworldtechnologyec.cedulamovil.domain.model.SuscripcionResponseDomain

fun SuscripcionResponseDto.toDomain() : SuscripcionResponseDomain = SuscripcionResponseDomain(
    suscripcionId = data.suscripcionId,
    consultasDisponibles = data.consultasDisponibles,
    isActive = data.isActive,
    fechaInicio = data.fechaInicio,
    plan = data.plan.toDomain()
)

fun PlanData.toDomain(): PlanDataDomain = PlanDataDomain(
    planId = planId,
    nombre = nombre,
    precio = precio,
    cantidadConsultasOriginal = cantidadConsultasOriginal
)

fun ListarRequestDomain.toData(): ListarRequestDto {
    return ListarRequestDto(
        cedula = this.cedula
    )
}

fun ListarResponseDto.toDomain(): ListarResponseDomain {
    return ListarResponseDomain(
        success = this.success,
        message = this.message,
        data = this.data.map { it.toDomain() }
    )
}

fun ListarData.toDomain(): ListarConsultaDomain {
    return ListarConsultaDomain(
        id = this.id,
        cedulaConsulta = this.cedulaConsulta,
        nombreCompleto = this.nombreCompleto,
        createdOn = this.createdOn
    )
}

fun DetalleRequestDomain.toData(): DetalleRequestDto {
    return DetalleRequestDto(
        id = this.id
    )
}

fun DetalleResponseDto.toDomain(): DetalleResponseDomain {
    val userData = this.data.detalle
    return DetalleResponseDomain(
        cedula = userData.cedula.orEmpty(),
        genero = userData.genero.orEmpty(),
        nombre = userData.nombre.orEmpty(),
        conyuge = userData.conyuge.orEmpty(),
        success = this.success && userData.success,
        profesion = userData.profesion.orEmpty(),
        estadoCivil = userData.estadoCivil.orEmpty(),
        instruccion = userData.instruccion.orEmpty(),
        nombreMadre = userData.nombreMadre.orEmpty(),
        nombrePadre = userData.nombrePadre.orEmpty(),
        nacionalidad = userData.nacionalidad.orEmpty(),
        calleDomicilio = userData.calleDomicilio.orEmpty(),
        lugarDomicilio = userData.lugarDomicilio.orEmpty(),
        fechaCedulacion = userData.fechaCedulacion.orEmpty(),
        fechaNacimiento = userData.fechaNacimiento.orEmpty(),
        lugarNacimiento = userData.lugarNacimiento.orEmpty(),
        condicionCedulado = userData.condicionCedulado.orEmpty(),
        numeracionDomicilio = userData.numeracionDomicilio.orEmpty(),
        fechaInscripcionGenero = userData.fechaInscripcionGenero.orEmpty(),
        lugarInscripcionGenero = userData.lugarInscripcionGenero.orEmpty(),
        fechaInscripcionDefuncion = userData.fechaInscripcionDefuncion.orEmpty()
    )
}

fun ConsultarRequestDomain.toData(): ConsultarRequestDto {
    return ConsultarRequestDto(
        identificacion = this.identificacion
    )
}

fun ConsultarResponseDto.toDomain(): ConsultarResponseDomain {
    val resultData = this.data
    return ConsultarResponseDomain(
        cedula = resultData.cedula,
        genero = resultData.genero,
        nombre = resultData.nombre,
        conyuge = resultData.conyuge,
        success = this.success && resultData.success,
        profesion = resultData.profesion,
        estadoCivil = resultData.estadoCivil,
        instruccion = resultData.instruccion,
        nombreMadre = resultData.nombreMadre,
        nombrePadre = resultData.nombrePadre,
        nacionalidad = resultData.nacionalidad,
        calleDomicilio = resultData.calleDomicilio,
        lugarDomicilio = resultData.lugarDomicilio,
        fechaCedulacion = resultData.fechaCedulacion,
        fechaNacimiento = resultData.fechaNacimiento,
        lugarNacimiento = resultData.lugarNacimiento,
        condicionCedulado = resultData.condicionCedulado,
        numeracionDomicilio = resultData.numeracionDomicilio,
        fechaInscripcionGenero = resultData.fechaInscripcionGenero,
        lugarInscripcionGenero = resultData.lugarInscripcionGenero,
        fechaInscripcionDefuncion = resultData.fechaInscripcionDefuncion
    )
}