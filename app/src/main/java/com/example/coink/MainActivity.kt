package com.example.coink

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.coink.databinding.ActivityMainBinding
import com.example.coink.utils.animAppear
import com.example.coink.utils.animVanish

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding

    val typedocument = arrayOf("Tipo de documento", "Cédula de ciudadania", "Cédula de extranjeria")
    val genero = arrayOf("Genero que aparece en tu documento", "Masculino", "Femenino")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initSplash()
        spinnerTypeDocument();
        spinnerGenero();
        enviarDatos();
    }


    /**
     * Metodo para el splash del inicio de aplicación
     */
    private fun initSplash() {
        Handler().postDelayed({
            mainBinding.splash.root.animVanish(this, 1000)
            despuesinit()
        }, 1000)
    }

    private fun despuesinit() {
        Handler().postDelayed({
            mainBinding.toolbar.animAppear(this, 3000)
            mainBinding.Verificar.animAppear(this, 3000)
            mainBinding.datosNecesarios.animAppear(this, 3000)
            mainBinding.spinnerTipoDocumento.animAppear(this, 3000)
            mainBinding.documento.animAppear(this, 3000)
            mainBinding.fecha.animAppear(this, 3000)
            mainBinding.fechaExpe.animAppear(this, 3000)
            mainBinding.spinnerGenero.animAppear(this, 3000)
            mainBinding.btnContinuar.animAppear(this, 3000)
        }, 1000)
    }

    /**
     * Metodo para hacer el spinner para el tipo de documento.
     */

    private fun spinnerTypeDocument() {
        val spinner = findViewById<Spinner>(R.id.spinnerTipoDocumento)
        val arrayAdapter =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, typedocument)
        spinner.adapter = arrayAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val spinner: Spinner = findViewById(R.id.spinnerTipoDocumento)
                spinner.onItemSelectedListener = this
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
    }

    /**
     * Metodo para hacer el spinner para el tipo de genero.
     */

    private fun spinnerGenero() {
        val spinner = findViewById<Spinner>(R.id.spinnerGenero)
        val arrayAdapter =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, genero)
        spinner.adapter = arrayAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val spinner: Spinner = findViewById(R.id.spinnerGenero)
                spinner.onItemSelectedListener = this
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }

    /**
     * Metodo para enviar los datos a la otra Activity
     */

    private fun enviarDatos() {
        mainBinding.btnContinuar.setOnClickListener {
            val continuar = Intent(this, VerificarDatos::class.java)
            continuar.putExtra("Datos", genero)
            startActivity(continuar)
        }
    }
}