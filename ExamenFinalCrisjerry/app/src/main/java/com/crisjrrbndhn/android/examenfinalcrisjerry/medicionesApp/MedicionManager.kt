package com.crisjrrbndhn.android.examenfinalcrisjerry.medicionesApp


import android.content.Context
import android.preference.PreferenceManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class MedicionManager {
    private val mediciones = mutableListOf<Medicion>()

    fun agregarMedicion(medicion: Medicion) {
        mediciones.add(medicion)
    }

    fun obtenerMediciones(): List<Medicion> {
        return mediciones.toList()
    }

    fun guardarMedicionesEnPreferencias(context: Context, mediciones: List<Medicion>) {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val medicionesJson = gson.toJson(mediciones)
        editor.putString("mediciones", medicionesJson)
        editor.apply()
    }
    fun cargarMedicionesDesdePreferencias(context: Context): List<Medicion> {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val medicionesJson = sharedPreferences.getString("mediciones", null)
        val gson = Gson()
        return if (medicionesJson != null) {
            val tipoLista = object : TypeToken<List<Medicion>>() {}.type
            gson.fromJson(medicionesJson, tipoLista)
        } else {
            emptyList()
        }

    }


}