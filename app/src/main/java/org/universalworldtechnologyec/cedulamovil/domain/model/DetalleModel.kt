package org.universalworldtechnologyec.cedulamovil.domain.model

data class DetalleRequestDomain(
    val id: String
)

data class DetalleResponseDomain(
    val cedula: String,
    val genero: String,
    val nombre: String,
    val conyuge: String,
    val success: Boolean,
    val profesion: String,
    val estadoCivil: String,
    val instruccion: String,
    val nombreMadre: String,
    val nombrePadre: String,
    val nacionalidad: String,
    val calleDomicilio: String,
    val lugarDomicilio: String,
    val fechaCedulacion: String,
    val fechaNacimiento: String,
    val lugarNacimiento: String,
    val condicionCedulado: String,
    val numeracionDomicilio: String,
    val fechaInscripcionGenero: String,
    val lugarInscripcionGenero: String,
    val fechaInscripcionDefuncion: String
)