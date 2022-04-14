package com.example.coink.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.coink.data.PayLoad
import com.example.coink.data.RetrofitAdapter
import com.example.coink.databinding.FragmentDatosCuentaBinding
import com.example.coink.utils.navigateTo
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException


class DatosCuenta : Fragment() {

    private lateinit var mainBinding: FragmentDatosCuentaBinding

    private val typedocument =
        arrayOf("Tipo de documento", "Cédula de ciudadania", "Cédula de extranjeria")
    private val genero = arrayOf("Genero que aparece en tu documento", "Masculino", "Femenino")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mainBinding = FragmentDatosCuentaBinding.inflate(inflater, container, false)
        spinnerTypeDocument()
        spinnerGenero()
        enviarDatos()
        return mainBinding.root
    }

    /**
     * Metodo para hacer el spinner para el tipo de documento.
     */

    private fun spinnerTypeDocument() {
        val spinner = mainBinding.spinnerTipoDocumento
        val spinnerAdapter = object : ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            typedocument
        ) {

            override fun isEnabled(position: Int): Boolean {
                // Disable the first item from Spinner
                // First item will be used for hint
                return position != 0
            }

            override fun getDropDownView(
                position: Int,
                convertView: View?,
                parent: ViewGroup
            ): View {
                val view: TextView =
                    super.getDropDownView(position, convertView, parent) as TextView
                //set the color of first item in the drop down list to gray
                if (position == 0) {
                    view.setTextColor(Color.GRAY)
                } else {
                    //here it is possible to define color for other items by
                    //view.setTextColor(Color.RED)
                }
                return view
            }

        }
        spinner.adapter = spinnerAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                spinner.onItemSelectedListener = this
            }

            override fun onNothingSelected(p0: AdapterView<*>?) = Unit

        }
    }

    /**
     * Metodo para hacer el spinner para el tipo de genero.
     */

    private fun spinnerGenero() {
        val spinner = mainBinding.spinnerGenero
        val spinnerAdapter = object : ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            genero
        ) {

            override fun isEnabled(position: Int): Boolean {
                // Disable the first item from Spinner
                // First item will be used for hint
                return position != 0
            }

            override fun getDropDownView(
                position: Int,
                convertView: View?,
                parent: ViewGroup
            ): View {
                val view: TextView =
                    super.getDropDownView(position, convertView, parent) as TextView
                //set the color of first item in the drop down list to gray
                if (position == 0) {
                    view.setTextColor(Color.GRAY)
                } else {
                    //here it is possible to define color for other items by
                    //view.setTextColor(Color.RED)
                }
                return view
            }

        }
        spinner.adapter = spinnerAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                spinner.onItemSelectedListener = this
            }

            override fun onNothingSelected(p0: AdapterView<*>?) = Unit

        }
    }

    /**
     * Metodo para enviar los datos a la otra Activity
     */

    private fun enviarDatos() {
        mainBinding.btnContinuar.setOnClickListener {
            val toJson = Gson().toJson(PayLoad("jsonEncryptado"))
            val result: Call<ResponseBody>? = RetrofitAdapter.apiService?.signup(toJson)
            result?.enqueue(object : Callback<ResponseBody?> {
                override fun onResponse(
                    call: Call<ResponseBody?>,
                    response: Response<ResponseBody?>
                ) {
                    try {
                        System.out.println("DatosCuenta" + response.body()?.string())
                        validateData()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }

                }

                override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                    Toast.makeText(requireContext(), t.message, Toast.LENGTH_LONG).show()
                }
            })
        }
    }

    /**
     * Meotod para validar que todos los campos contengan información
     */

    private fun validateData() {
        when {
            mainBinding.spinnerTipoDocumento.selectedItemPosition == 0 -> {
                Toast.makeText(requireContext(), "Llene campo Tipo de Documento", Toast.LENGTH_LONG)
                    .show()
            }
            mainBinding.documento.text.isEmpty() -> {
                Toast.makeText(requireContext(), "Llene campo Documento", Toast.LENGTH_LONG).show()
            }
            mainBinding.fecha.text.isEmpty() -> {
                Toast.makeText(requireContext(), "Llene campo Fecha", Toast.LENGTH_LONG).show()
            }
            mainBinding.fechaExpe.text.isEmpty() -> {
                Toast.makeText(requireContext(), "Llene campo Fecha", Toast.LENGTH_LONG).show()
            }
            mainBinding.spinnerGenero.selectedItemPosition == 0 -> {
                Toast.makeText(requireContext(), "Llene campo Genero", Toast.LENGTH_LONG).show()
            }
            else -> {
                navigateTo(DatosCuentaDirections.irVerificar(mainBinding.spinnerGenero.selectedItem.toString()))
            }
        }
    }
}