package com.crisjrrbndhn.android.examenfinalcrisjerry

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import com.crisjrrbndhn.android.examenfinalcrisjerry.R
import com.crisjrrbndhn.android.examenfinalcrisjerry.medicionesApp.Medicion


class NuevaMedicionActivity : AppCompatActivity() {

    private lateinit var etValorMedidor: EditText
    private lateinit var etFecha: EditText
    private lateinit var cbAgua: CheckBox
    private lateinit var cbLuz: CheckBox
    private lateinit var cbGas: CheckBox
    private lateinit var btnRegistrarMedicion: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nueva_medicion)

        etValorMedidor = findViewById(R.id.Medidor)
        etFecha = findViewById(R.id.Fecha)
        cbAgua = findViewById(R.id.rbAgua)
        cbLuz = findViewById(R.id.rbLuz)
        cbGas = findViewById(R.id.rbGas)
        btnRegistrarMedicion = findViewById(R.id.btnRegistrarMedicion)

        btnRegistrarMedicion.setOnClickListener {
            val tipoGasto = when {
                cbAgua.isChecked -> "Agua"
                cbLuz.isChecked -> "Luz"
                cbGas.isChecked -> "Gas"
                else -> "Otro"
            }

            val valorMedidor = etValorMedidor.text.toString().toFloat()
            val fecha = etFecha.text.toString()

            val nuevaMedicion = Medicion(tipoGasto, valorMedidor, fecha)

            val resultIntent = Intent().apply {
                putExtra("nuevaMedicion", nuevaMedicion)
            }
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}