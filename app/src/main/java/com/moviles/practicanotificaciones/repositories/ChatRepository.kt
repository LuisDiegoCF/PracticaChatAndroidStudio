package com.moviles.practicanotificaciones.repositories

import com.moviles.practicanotificaciones.api.CHATPlaceHolderAPI
import com.moviles.practicanotificaciones.models.*
import retrofit2.Call

object ChatRepository {

    fun iniciarSesion(usuario: Usuario, listener: InitSesionListener) {
        val retrofit = RetrofitRepository.getRetrofit()
        val chatPlaceHolderAPI = retrofit.create(CHATPlaceHolderAPI::class.java)
        chatPlaceHolderAPI.iniciarSesion(usuario).enqueue(object : retrofit2.Callback<Respuesta> {
            override fun onResponse(
                call: Call<Respuesta>,
                response: retrofit2.Response<Respuesta>
            ) {
                if (response.isSuccessful) {
                    listener.onUsuarioSuccess(response.body())
                }
            }

            override fun onFailure(call: Call<Respuesta>, t: Throwable) {
                listener.onUsuarioFailure(t)
            }
        })
    }

    fun getCharlas(id: Int, listener: ChatListListener) {
        val retrofit = RetrofitRepository.getRetrofit()
        val chatPlaceHolderAPI = retrofit.create(CHATPlaceHolderAPI::class.java)
        chatPlaceHolderAPI.getCharlas(id).enqueue(object : retrofit2.Callback<Respuesta> {
            override fun onResponse(
                call: Call<Respuesta>,
                response: retrofit2.Response<Respuesta>
            ) {
                if (response.isSuccessful) {
                    listener.onChatListSuccess(response.body())
                }
            }

            override fun onFailure(call: Call<Respuesta>, t: Throwable) {
                listener.onChatListFailure(t)
            }
        })
    }

    fun addMessage(mensaje: Mensaje, listener: ChatEnviarMensajeListener) {
        val retrofit = RetrofitRepository.getRetrofit()
        val chatPlaceHolderAPI = retrofit.create(CHATPlaceHolderAPI::class.java)
        chatPlaceHolderAPI.enviarMensaje(mensaje).enqueue(object : retrofit2.Callback<Respuesta> {
            override fun onResponse(
                call: Call<Respuesta>,
                response: retrofit2.Response<Respuesta>
            ) {
                if (response.isSuccessful) {
                    listener.onChatCreateSuccess(response.body())
                }
            }

            override fun onFailure(call: Call<Respuesta>, t: Throwable) {
                listener.onChatCreateFailure(t)
            }
        })
    }

    fun addCharla(conversacion: Conversacion, listener: ChatCreateCharlaListener) {
        val retrofit = RetrofitRepository.getRetrofit()
        val chatPlaceHolderAPI = retrofit.create(CHATPlaceHolderAPI::class.java)
        chatPlaceHolderAPI.crearCharla(conversacion).enqueue(object : retrofit2.Callback<Respuesta> {
            override fun onResponse(
                call: Call<Respuesta>,
                response: retrofit2.Response<Respuesta>
            ) {
                if (response.isSuccessful) {
                    listener.onChatCreateSuccess(response.body())
                }
            }

            override fun onFailure(call: Call<Respuesta>, t: Throwable) {
                listener.onChatCreateFailure(t)
            }
        })
    }

    fun getChat(id: Int, listener: ChatByIdListener) {
        val retrofit = RetrofitRepository.getRetrofit()
        val chatPlaceHolderAPI = retrofit.create(CHATPlaceHolderAPI::class.java)
        chatPlaceHolderAPI.getChat(id).enqueue(object : retrofit2.Callback<Chat> {
            override fun onResponse(
                call: Call<Chat>,
                response: retrofit2.Response<Chat>
            ) {
                if (response.isSuccessful) {
                    listener.onChatByIdSuccess(response.body())
                }
            }

            override fun onFailure(call: Call<Chat>, t: Throwable) {
                listener.onChatByIdFailure(t)
            }
        })
    }

    interface InitSesionListener {
        fun onUsuarioSuccess(respuesta: Respuesta?)
        fun onUsuarioFailure(t: Throwable)
    }

    interface ChatListListener {
        fun onChatListSuccess(respuesta: Respuesta?)
        fun onChatListFailure(t: Throwable)
    }

    interface ChatEnviarMensajeListener {
        fun onChatCreateSuccess(respuesta: Respuesta?)
        fun onChatCreateFailure(t: Throwable)
    }

    interface ChatCreateCharlaListener {
        fun onChatCreateSuccess(respuesta: Respuesta?)
        fun onChatCreateFailure(t: Throwable)
    }

    interface ChatByIdListener {
        fun onChatByIdSuccess(chat: Chat?)
        fun onChatByIdFailure(t: Throwable)
    }
}