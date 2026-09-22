package org.universalworldtechnologyec.cedulamovil.domain.model

data class SuscripcionResponseDomain(
    val suscripcionId: Int,
    val consultasDisponibles: Int = 0,
    val isActive: Boolean = false,
    val fechaInicio: String = "",
    val plan: PlanDataDomain? = null
)


data class PlanDataDomain(
    val planId: Int = 0,
    val nombre: String = "",
    val precio: Double = 0.0,
    val cantidadConsultasOriginal: Int = 0
)