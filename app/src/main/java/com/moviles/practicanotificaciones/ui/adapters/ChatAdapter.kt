package com.moviles.practicanotificaciones.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.moviles.practicanotificaciones.R
import com.moviles.practicanotificaciones.models.MensajeConversacion

class ChatAdapter(val data: ArrayList<MensajeConversacion>) : RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {
    class ChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val lblMensaje = itemView.findViewById<TextView>(R.id.lblMensaje)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.chat_item_layout, parent, false)
        return ChatViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val mensaje = data[position]
        holder.lblMensaje.text = mensaje.mensaje
    }

    override fun getItemCount(): Int {
        return data.size
    }
}