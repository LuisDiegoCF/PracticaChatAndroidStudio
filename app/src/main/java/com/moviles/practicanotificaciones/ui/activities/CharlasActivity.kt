package com.moviles.practicanotificaciones.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.moviles.practicanotificaciones.R
import com.moviles.practicanotificaciones.api.CHATPlaceHolderAPI
import com.moviles.practicanotificaciones.models.Charla
import com.moviles.practicanotificaciones.models.Respuesta
import com.moviles.practicanotificaciones.repositories.ChatRepository
import com.moviles.practicanotificaciones.ui.adapters.CharlaAdapter

class CharlasActivity : AppCompatActivity(),
    ChatRepository.ChatListListener,
    CharlaAdapter.OnCharlaClickListener {
    private lateinit var lstCharlas: RecyclerView
    private lateinit var btnAddCharla: FloatingActionButton
    private var idUsuario: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_charlas)
        btnAddCharla = findViewById(R.id.btnAddCharla)
        lstCharlas = findViewById(R.id.lstCharlas)
        idUsuario = intent.getIntExtra("idUsuario", 0)
        cargarCharlas()
        setupEventListeners()
    }

    private fun cargarCharlas() {
        ChatRepository.getCharlas(idUsuario, this)
    }

    private fun setupEventListeners() {
        btnAddCharla.setOnClickListener {
            val intent = Intent(this, FormCharlaActivity::class.java)
            intent.putExtra("idUsuario", idUsuario)
            startActivity(intent)
        }
    }

    override fun onChatListSuccess(respuesta: Respuesta?) {
        lstCharlas.layoutManager = LinearLayoutManager(this@CharlasActivity)
        lstCharlas.adapter = CharlaAdapter(respuesta!!.charlas, this)
    }

    override fun onChatListFailure(t: Throwable) {
        Log.d("CharlasActivity", "Error: ${t.message}")
    }

    override fun onCharlaClick(charla: Charla) {
        val intent = Intent(this, ChatActivity::class.java)
        intent.putExtra("idCharla", charla.id)
        intent.putExtra("idUsuarioRecepcion", charla.idUsuarioRecepcion)
        intent.putExtra("idUsuario", idUsuario)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        cargarCharlas()
    }
}