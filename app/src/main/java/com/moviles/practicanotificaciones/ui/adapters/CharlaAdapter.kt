package com.moviles.practicanotificaciones.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.moviles.practicanotificaciones.R
import com.moviles.practicanotificaciones.models.Charla
import com.moviles.practicanotificaciones.models.Respuesta

class CharlaAdapter(val data: ArrayList<Charla>, val listener: OnCharlaClickListener): RecyclerView.Adapter<CharlaAdapter.CharlaViewHolder>() {
    class CharlaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val lblId = itemView.findViewById<TextView>(R.id.lblId)
        val lblReceptor = itemView.findViewById<TextView>(R.id.lblReceptor)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharlaViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.charla_item_layout, parent, false)
        return CharlaViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharlaViewHolder, position: Int) {
        val charla = data[position]
        holder.lblId.text = charla.id.toString()
        holder.lblReceptor.text = charla.nombreReceptor
        holder.itemView.setOnClickListener {
            listener.onCharlaClick(charla)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    interface OnCharlaClickListener {
        fun onCharlaClick(charla: Charla)
    }
}