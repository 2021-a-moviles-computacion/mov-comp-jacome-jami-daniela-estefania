package com.example.instagram.adaptor

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.instagram.fragment.Profile
import com.example.instagram.R
import com.example.instagram.entrenador.PhotosEntrenador

class PhotoAdaptor(
    private val context: Profile,
    private val listaEntrenador: List<PhotosEntrenador>,
    private val recyclerView: RecyclerView
) : RecyclerView.Adapter<PhotoAdaptor.MyViewHolder>() {
    @SuppressLint("CutPasteId")
    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        //la clase que define el caparazón
        var username: TextView?
        var options: ImageView?
        var numero_posts: TextView?
        var numero_followers: TextView?
        var numero_following: TextView?
        var editarPerfil: Button?
        var fullname: TextView?
        var bio: TextView?
        var my_fotos: ImageButton?
        var saved_fotos: ImageButton?

        init{
            this.username = view.findViewById(R.id.profile_username)
            this.options = view.findViewById(R.id.options)
            this.numero_posts = view.findViewById(R.id.posts)
            this.numero_followers = view.findViewById(R.id.followers)
            this.numero_following = view.findViewById(R.id.following)
            this.editarPerfil = view.findViewById(R.id.edit_profile)
            this.fullname = view.findViewById(R.id.fullname)
            this.numero_followers = view.findViewById(R.id.followers)
            this.numero_following = view.findViewById(R.id.following)
            this.bio = view.findViewById(R.id.bio)
            this.my_fotos = view.findViewById(R.id.my_fotos)
            this.numero_following = view.findViewById(R.id.following)
            this.saved_fotos = view.findViewById(R.id.saved_fotos)

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
                R.layout.item_photo, //definir vista del recycler view
                parent,
                false
            )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val entrenador = listaEntrenador[position]
      //  holder.my_fotos.setImageResource(entrenador.foto)
    }

    override fun getItemCount(): Int {
        return listaEntrenador.size
    }
}