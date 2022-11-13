package com.moviles.practicanotificaciones.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.moviles.practicanotificaciones.R
import com.moviles.practicanotificaciones.databinding.ActivityChatBinding
import com.moviles.practicanotificaciones.models.Chat
import com.moviles.practicanotificaciones.models.Mensaje
import com.moviles.practicanotificaciones.models.MensajeConversacion
import com.moviles.practicanotificaciones.models.Respuesta
import com.moviles.practicanotificaciones.repositories.ChatRepository
import com.moviles.practicanotificaciones.ui.adapters.ChatAdapter

class ChatActivity : AppCompatActivity(),
    ChatRepository.ChatByIdListener,
    ChatRepository.ChatEnviarMensajeListener {
    private lateinit var binding : ActivityChatBinding
    private var idUsuario: Int = 0
    private var idCharla: Int = 0
    private var idUsuarioRecepcion: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        idUsuario = intent.getIntExtra("idUsuario", 0)
        idCharla = intent.getIntExtra("idCharla", 0)
        idUsuarioRecepcion = intent.getIntExtra("idUsuarioRecepcion", 0)
        cargarMensajes()
        setupEventListeners()
    }

    private fun cargarMensajes() {
        ChatRepository.getChat(idCharla, this)
    }

    private fun setupEventListeners() {
        binding.btnEnviar.setOnClickListener {
            val mensaje = binding.txtMensaje.text.toString()
            var objMensaje = Mensaje(idUsuario, idUsuarioRecepcion, mensaje, idCharla)
            ChatRepository.addMessage(objMensaje, this)
        }
    }

    override fun onChatByIdSuccess(chat: Chat?) {
        binding.lstMensajes.layoutManager = LinearLayoutManager(this@ChatActivity)
        binding.lstMensajes.adapter = ChatAdapter(chat!!.charla)
    }

    override fun onChatByIdFailure(t: Throwable) {
        Log.d("ChatActivity", "Error: ${t.message}")
    }

    override fun onResume() {
        super.onResume()
        cargarMensajes()
    }

    override fun onChatCreateSuccess(respuesta: Respuesta?) {
        binding.txtMensaje.setText("")
        cargarMensajes()
    }

    override fun onChatCreateFailure(t: Throwable) {
        Log.d("ERROR", "Error: ${t.message}")
        Toast.makeText(this, t.message, Toast.LENGTH_SHORT).show()
    }
}