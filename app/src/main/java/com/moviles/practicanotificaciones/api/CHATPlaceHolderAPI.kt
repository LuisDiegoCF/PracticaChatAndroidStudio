package com.moviles.practicanotificaciones.api

import com.moviles.practicanotificaciones.models.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CHATPlaceHolderAPI {

    @POST("iniciarsesion")
    fun iniciarSesion(@Body usuario: Usuario): Call<Respuesta>

    @GET("getcharlas/{id}")
    fun getCharlas(@Path("id") id: Int): Call<Respuesta>

    @POST("addmessage")
    fun enviarMensaje(@Body mensaje: Mensaje): Call<Respuesta>

    @POST("addcharla")
    fun crearCharla(@Body conversacion: Conversacion): Call<Respuesta>

    @GET("getchat/{id}")
    fun getChat(@Path("id") id: Int): Call<Chat>

}