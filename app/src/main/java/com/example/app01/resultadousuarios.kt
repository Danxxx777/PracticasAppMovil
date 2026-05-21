package com.example.app01

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.app01.databinding.ActivityResultadoBinding

class resultadousuarios : AppCompatActivity() {

    private lateinit var binding: ActivityResultadoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultadoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvNombres.text   = "Nombres: ${intent.getStringExtra("nombres")}"
        binding.tvApellidos.text = "Apellidos: ${intent.getStringExtra("apellidos")}"
        binding.tvCorreo.text    = "Correo: ${intent.getStringExtra("correo")}"
        binding.tvTelefono.text  = "Teléfono: ${intent.getStringExtra("telefono")}"
        binding.tvFecha.text     = "Fecha Nac.: ${intent.getStringExtra("fecha")}"
        binding.tvTipoDoc.text   = "Tipo Doc.: ${intent.getStringExtra("tipoDoc")}"
        binding.tvNumDoc.text    = "Núm. Doc.: ${intent.getStringExtra("numDoc")}"
        binding.tvCiudad.text    = "Ciudad: ${intent.getStringExtra("ciudad")}"
        binding.tvGenero.text    = "Género: ${intent.getStringExtra("genero")}"
    }
}