package com.example.instagram.adaptor

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.instagram.R
import com.example.instagram.entrenador.PhotosEntrenador
import com.example.instagram.fragment.Search

class SearchAdaptor(
    private val context: Search,
    private val listaEntrenador: List<PhotosEntrenador>,
    private val recyclerView: RecyclerView
) : RecyclerView.Adapter<SearchAdaptor.MyViewHolder>() {
    @SuppressLint("CutPasteId")
    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        //la clase que define el caparazón
        var searchbar: EditText?

        var photo: ImageView

        init{
            this.searchbar = view.findViewById(R.id.search_bar)
            this.photo = view.findViewById(R.id.photo_items)
        }
/*
        fun anadirLike()
        {
            this.numeroLikes= this.numeroLikes+1
            likesTextView.text= this.numeroLikes.toString()
           // contexto.aumentarTotalLikes()
        }*/
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.item_photo, //definir vista del recycler view
                parent,
                false
            )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val entrenador = listaEntrenador[position]
        holder.photo.setImageResource(entrenador.foto)
    }

    override fun getItemCount(): Int {
        return listaEntrenador.size
    }
}