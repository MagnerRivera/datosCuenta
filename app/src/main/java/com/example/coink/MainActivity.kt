package com.example.coink

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.coink.databinding.ActivityMainBinding
import com.example.coink.utils.animAppear
import com.example.coink.utils.animVanish

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initSplash()
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
            mainBinding.genero.animAppear(this, 3000)
            mainBinding.btnContinuar.animAppear(this, 3000)
        }, 1000)
    }
}