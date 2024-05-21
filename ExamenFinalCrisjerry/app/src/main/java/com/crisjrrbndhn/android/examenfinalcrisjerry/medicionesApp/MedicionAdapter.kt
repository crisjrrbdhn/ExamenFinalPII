package com.crisjrrbndhn.android.examenfinalcrisjerry.medicionesApp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.crisjrrbndhn.android.examenfinalcrisjerry.R

class MedicionAdapter(context: Context, private val mediciones: List<Medicion>) :
    ArrayAdapter<Medicion>(context, 0, mediciones) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.list_item_medicion, parent, false)

        val medicion = mediciones[position]

        val ivIcono = view.findViewById<ImageView>(R.id.ivIcono)
        val tvTipoGasto = view.findViewById<TextView>(R.id.tvTipoGasto)
        val tvValorMedidor = view.findViewById<TextView>(R.id.tvValorMedidor)
        val tvFecha = view.findViewById<TextView>(R.id.tvFecha)

        tvTipoGasto.text = medicion.tipoGasto
        tvValorMedidor.text = medicion.valorMedidor.toString()
        tvFecha.text = medicion.fecha

        val iconResId = when (medicion.tipoGasto) {
            "Agua" -> R.drawable.ic_agua
            "Luz" -> R.drawable.ic_luz
            "Gas" -> R.drawable.ic_gas
            else -> R.drawable.guardado
        }
        ivIcono.setImageResource(iconResId)

        return view
    }
}