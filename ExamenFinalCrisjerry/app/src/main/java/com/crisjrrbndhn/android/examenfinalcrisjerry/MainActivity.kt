package com.crisjrrbndhn.android.examenfinalcrisjerry

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.compose.runtime.Composable
import com.crisjrrbndhn.android.examenfinalcrisjerry.medicionesApp.Medicion
import com.crisjrrbndhn.android.examenfinalcrisjerry.medicionesApp.MedicionAdapter
import com.crisjrrbndhn.android.examenfinalcrisjerry.medicionesApp.MedicionManager
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {

    private val mediciones = mutableListOf<Medicion>()
    private lateinit var adapter: MedicionAdapter
    private lateinit var medicionManager: MedicionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        medicionManager = MedicionManager()
        val medicionesGuardadas = medicionManager.cargarMedicionesDesdePreferencias(this)
        mediciones.addAll(medicionesGuardadas)

        val listViewMediciones: ListView = findViewById(R.id.listViewMediciones)
        val fabAdd: FloatingActionButton = findViewById(R.id.fabAdd)

        adapter = MedicionAdapter(this, mediciones)
        listViewMediciones.adapter = adapter

        fabAdd.setOnClickListener {
            val intent = Intent(this, NuevaMedicionActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            val nuevaMedicion = data?.getSerializableExtra("nuevaMedicion") as? Medicion
            nuevaMedicion?.let {
                mediciones.add(it)
                adapter.notifyDataSetChanged()

                // Agregamos la nueva medición al MedicionManager y guardamos todas las mediciones
                medicionManager.agregarMedicion(it)
                medicionManager.guardarMedicionesEnPreferencias(this, mediciones)
            }
        }
    }
    companion object {
        const val REQUEST_CODE = 1
    }
}

