package com.example.coink

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.coink.databinding.VerificarDatosBinding


class VerificarDatos : AppCompatActivity() {

    private lateinit var mainBinding: VerificarDatosBinding


    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView(this, R.layout.verificar_datos)
        val bundle = intent.extras
        val dato = bundle?.getString("Datos")
        mainBinding.traerGenero.text = dato

        mainBinding.btnCancelar.setOnClickListener {
            val lanzar = Intent(this, MainActivity::class.java)
            startActivity(lanzar)
        }
    }

}