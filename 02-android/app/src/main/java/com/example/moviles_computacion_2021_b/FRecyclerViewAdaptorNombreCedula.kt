package com.example.moviles_computacion_2021_b

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FRecyclerViewAdaptorNombreCedula(
    private val contexto: GRecyclerView,
    private val listaEntrenador: List<BEntrenador>,
    private val recyclerView: RecyclerView
) : RecyclerView.Adapter<FRecyclerViewAdaptorNombreCedula.MyViewHolder>() {
    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        //la clase que define el caparazón
        val nombreTextView: TextView
        val cedulaTextView: TextView
        var likesTextView: TextView
        val accionButton: Button
        var numeroLikes=0

        init{
            this.nombreTextView = view.findViewById(R.id.tv_nombre)
            this.accionButton = view.findViewById(R.id.btn_dar_like)
            this.cedulaTextView = view.findViewById(R.id.tv_cedula)
            this.likesTextView = view.findViewById(R.id.tv_likes)
            accionButton.setOnClickListener {
                this.anadirLike()
            }
        }

        fun anadirLike()
        {
            this.numeroLikes= this.numeroLikes+1
            likesTextView.text= this.numeroLikes.toString()
            contexto.aumentarTotalLikes()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.recycler_view_vista, //definir vista del recycler view
                parent,
                false
            )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val entrenador = listaEntrenador[position]
        holder.nombreTextView.text = entrenador.nombre
        holder.cedulaTextView.text = entrenador.descripcion
        holder.accionButton.text ="Like ${entrenador.nombre}"
        holder.likesTextView.text="0"
    }

    override fun getItemCount(): Int {
        return listaEntrenador.size
    }


}