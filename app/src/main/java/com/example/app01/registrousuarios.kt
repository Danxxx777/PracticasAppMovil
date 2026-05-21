package com.example.app01

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.app01.databinding.RegistrousuariosBinding
import java.util.Calendar

class registrousuarios : AppCompatActivity() {

    private lateinit var binding: RegistrousuariosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RegistrousuariosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //documento
        val tiposDoc = listOf("Cédula", "Pasaporte", "RUC", "Otro")
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, tiposDoc)
        binding.acTipoDoc.setAdapter(adapter)

        //  fecha
        binding.etFecha.setOnClickListener {
            val cal = Calendar.getInstance()
            DatePickerDialog(this, { _, y, m, d ->
                binding.etFecha.setText("$d/${m + 1}/$y")
            }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show()
        }
        binding.tilFecha.setEndIconOnClickListener {
            binding.etFecha.performClick()
        }

        //  limpiar
        binding.btnLimpiar.setOnClickListener {
            binding.etNombres.text?.clear()
            binding.etApellidos.text?.clear()
            binding.etCorreo.text?.clear()
            binding.etTelefono.text?.clear()
            binding.etFecha.text?.clear()
            binding.acTipoDoc.text?.clear()
            binding.etNumDoc.text?.clear()
            binding.etCiudad.text?.clear()
            binding.rgGenero.clearCheck()

            binding.tilNombres.error = null
            binding.tilApellidos.error = null
            binding.tilCorreo.error = null
            binding.tilTelefono.error = null
            binding.tilFecha.error = null
            binding.tilTipoDoc.error = null
            binding.tilNumDoc.error = null
            binding.tilCiudad.error = null
        }

        // enviar
        binding.btnEnviar.setOnClickListener {
            if (validar()) {
                val genero = when (binding.rgGenero.checkedRadioButtonId) {
                    R.id.rbMasculino -> "Masculino"
                    R.id.rbFemenino -> "Femenino"
                    R.id.rbOtro -> "Otro"
                    else -> ""
                }
                val intent = Intent(this, resultadousuarios::class.java).apply {
                    putExtra("nombres", binding.etNombres.text.toString())
                    putExtra("apellidos", binding.etApellidos.text.toString())
                    putExtra("correo", binding.etCorreo.text.toString())
                    putExtra("telefono", binding.etTelefono.text.toString())
                    putExtra("fecha", binding.etFecha.text.toString())
                    putExtra("tipoDoc", binding.acTipoDoc.text.toString())
                    putExtra("numDoc", binding.etNumDoc.text.toString())
                    putExtra("ciudad", binding.etCiudad.text.toString())
                    putExtra("genero", genero)
                }
                startActivity(intent)
            }
        }
    }

    private fun validar(): Boolean {
        var ok = true

        val nombres = binding.etNombres.text.toString()
        if (nombres.isEmpty()) {
            binding.tilNombres.error = "Los nombres son obligatorios"
            ok = false
        } else {
            binding.tilNombres.error = null
        }

        val apellidos = binding.etApellidos.text.toString()
        if (apellidos.isEmpty()) {
            binding.tilApellidos.error = "Los apellidos son obligatorios"
            ok = false
        } else {
            binding.tilApellidos.error = null
        }

        val correo = binding.etCorreo.text.toString()
        if (correo.isEmpty()) {
            binding.tilCorreo.error = "El correo es obligatorio"
            ok = false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            binding.tilCorreo.error = "Correo no válido"
            ok = false
        } else {
            binding.tilCorreo.error = null
        }

        val telefono = binding.etTelefono.text.toString()
        if (telefono.isEmpty()) {
            binding.tilTelefono.error = "El teléfono es obligatorio"
            ok = false
        } else if (telefono.length < 10) {
            binding.tilTelefono.error = "Teléfono debe tener al menos 10 dígitos"
            ok = false
        } else {
            binding.tilTelefono.error = null
        }

        val fecha = binding.etFecha.text.toString()
        if (fecha.isEmpty()) {
            binding.tilFecha.error = "Selecciona la fecha de nacimiento"
            ok = false
        } else {
            binding.tilFecha.error = null
        }

        val tipoDoc = binding.acTipoDoc.text.toString()
        if (tipoDoc.isEmpty()) {
            binding.tilTipoDoc.error = "Selecciona el tipo de documento"
            ok = false
        } else {
            binding.tilTipoDoc.error = null
        }

        val numDoc = binding.etNumDoc.text.toString()
        if (numDoc.isEmpty()) {
            binding.tilNumDoc.error = "El número de documento es obligatorio"
            ok = false
        } else if (numDoc.length < 10) {
            binding.tilNumDoc.error = "Debe tener al menos 10 dígitos"
            ok = false
        } else {
            binding.tilNumDoc.error = null
        }

        val ciudad = binding.etCiudad.text.toString()
        if (ciudad.isEmpty()) {
            binding.tilCiudad.error = "La ciudad es obligatoria"
            ok = false
        } else {
            binding.tilCiudad.error = null
        }

        if (binding.rgGenero.checkedRadioButtonId == -1) {
            Toast.makeText(this, "Selecciona un género", Toast.LENGTH_SHORT).show()
            ok = false
        }

        return ok
    }
}