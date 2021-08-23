package com.example.instagram.adaptor

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.instagram.R
import com.example.instagram.fragment.Notification

class NotificationAdaptor(
    private val context: Notification,
    private val listaEntrenador: List<*>,
    private val recyclerView: RecyclerView
) : RecyclerView.Adapter<NotificationAdaptor.MyViewHolder>() {
    @SuppressLint("CutPasteId")
    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        //la clase que define el caparazón
        var user_notification: TextView;
        var description_notification: TextView;

        init{
            this.user_notification = view.findViewById(R.id.user_notification)
            this.description_notification = view.findViewById(R.id.description_notification)

          /*  this.likes = view.findViewById(R.id.tv_likes)
            accionButton.setOnClickListener {
                this.anadirLike()
            }*/

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
                R.layout.item_notifications, //definir vista del recycler view
                parent,
                false
            )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return listaEntrenador.size
    }
}