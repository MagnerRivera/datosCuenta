package com.example.coink.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.coink.R
import com.example.coink.databinding.FragmentVerificarDatosBinding
import com.example.coink.utils.navigateBack

class VerificarDatos : Fragment() {
    private lateinit var biding: FragmentVerificarDatosBinding
    val args: VerificarDatosArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        biding = FragmentVerificarDatosBinding.inflate(inflater, container, false)
        biding.traerGenero.text = args.genero
        biding.includeTolbar.apply {
            imgback.setOnClickListener {
                navigateBack()
            }
            check1.setImageDrawable(
                ResourcesCompat.getDrawable(
                    requireActivity().resources,
                    R.drawable.sinseleccionar,
                    null
                )
            )
            check2.setImageDrawable(
                ResourcesCompat.getDrawable(
                    requireActivity().resources,
                    R.drawable.conseleccionar,
                    null
                )
            )
        }
        biding.btnCancelar.setOnClickListener {
            navigateBack()
        }
        return biding.root
    }
}