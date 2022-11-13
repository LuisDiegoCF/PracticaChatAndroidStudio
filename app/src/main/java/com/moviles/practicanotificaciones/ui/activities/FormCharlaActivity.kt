package com.moviles.practicanotificaciones.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.moviles.practicanotificaciones.R
import com.moviles.practicanotificaciones.databinding.ActivityFormCharlaBinding
import com.moviles.practicanotificaciones.models.Conversacion
import com.moviles.practicanotificaciones.models.Respuesta
import com.moviles.practicanotificaciones.repositories.ChatRepository

class FormCharlaActivity : AppCompatActivity(),
    ChatRepository.ChatCreateCharlaListener {
    private lateinit var binding : ActivityFormCharlaBinding
    private var idUsuario: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormCharlaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        idUsuario = intent.getIntExtra("idUsuario", 0)
        binding.txtIdEmisor.setText(idUsuario.toString())
        setupEventListeners()
    }

    private fun setupEventListeners() {
        binding.btnSave.setOnClickListener {
            val idUsuario = binding.txtIdEmisor.text.toString().toInt()
            val idReceptor = binding.txtIdReceptor.text.toString().toInt()
            var conversacion = Conversacion(idUsuario, idReceptor)
            ChatRepository.addCharla(conversacion, this)
        }
        binding.btnCancel.setOnClickListener {
            finish()
        }
    }

    override fun onChatCreateSuccess(respuesta: Respuesta?) {
        finish()
    }

    override fun onChatCreateFailure(t: Throwable) {
        finish()
    }
}