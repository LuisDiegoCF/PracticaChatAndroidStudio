package com.moviles.practicanotificaciones.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.moviles.practicanotificaciones.R
import com.moviles.practicanotificaciones.models.Respuesta
import com.moviles.practicanotificaciones.models.Usuario
import com.moviles.practicanotificaciones.repositories.ChatRepository

class MainActivity : AppCompatActivity(),
    ChatRepository.InitSesionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupFireBase()
    }

    private val idUsuario = 27

    private fun setupFireBase() {
        // el token es un identificador unico para cada dispositivo que se registra en la app
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("TOKEN", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result as String

            // Log and toast
            Log.d("TOKEN", token)
            val usuario = Usuario(idUsuario, token)
            iniciarSesion(usuario)
            //Toast.makeText(baseContext, token, Toast.LENGTH_SHORT).show()
        })
    }

    private fun iniciarSesion(usuario: Usuario) {
        ChatRepository.iniciarSesion(usuario, this)
    }

    override fun onUsuarioSuccess(respuesta: Respuesta?) {
        Log.d("USUARIO", respuesta!!.res + " " + respuesta.valor)
        Toast.makeText(this, respuesta.res + " " + respuesta.valor, Toast.LENGTH_SHORT).show()
        if (respuesta.res == "success") {
            // abrir la actividad de charlas
            val intent = Intent(this, CharlasActivity::class.java)
            intent.putExtra("idUsuario", idUsuario)
            startActivity(intent)
        }
    }

    override fun onUsuarioFailure(t: Throwable) {
        Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
    }
}