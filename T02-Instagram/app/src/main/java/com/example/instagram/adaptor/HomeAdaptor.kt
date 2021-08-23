package com.example.instagram.adaptor

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.instagram.fragment.Home
import com.example.instagram.R
import com.example.instagram.entrenador.HomeEntrenador

class HomeAdaptor(
    private val context: Home,
    private val listaEntrenador: List<HomeEntrenador>,
    private val recyclerView: RecyclerView
) : RecyclerView.Adapter<HomeAdaptor.MyViewHolder>() {

    @SuppressLint("CutPasteId")
    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        //la clase que define el caparazón
        var username: TextView
        val photo: ImageView

        var num_likes: ImageButton
        var likesTextView: TextView

        var username_publisher: TextView
        var description: TextView?

        var numeroLikes=0

        init{
            this.username = view.findViewById(R.id.profile_username)
            this.photo = view.findViewById(R.id.post_image)

            this.num_likes = view.findViewById(R.id.post_img_like)
            num_likes.setOnClickListener {
                this.anadirLike()
            }
            this.likesTextView = view.findViewById(R.id.post_txtv_likes)

            this.username_publisher = view.findViewById(R.id.post_username_publisher)
            this.description = view.findViewById(R.id.post_description)

        }

        fun anadirLike()
        {
            this.numeroLikes= this.numeroLikes+1
            likesTextView.text= this.numeroLikes.toString()
           // context.aumentarTotalLikes()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.item_post, //definir vista del recycler view
                parent,
                false
            )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       val entrenador = listaEntrenador[position]
        holder.username.text = entrenador.nombre
        holder.photo.setImageResource(entrenador.foto)
        holder.description!!.text  = entrenador.descripcion
        holder.likesTextView.text =entrenador.likes.toString()
        holder.username_publisher.text = entrenador.nombre
    }

    override fun getItemCount(): Int {
        return listaEntrenador.size
    }
}