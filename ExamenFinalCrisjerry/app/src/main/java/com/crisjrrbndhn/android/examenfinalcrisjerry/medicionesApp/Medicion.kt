package com.crisjrrbndhn.android.examenfinalcrisjerry.medicionesApp

import java.io.Serializable

data class Medicion(
    val tipoGasto: String,
    val valorMedidor: Float,
    val fecha: String
) : Serializable