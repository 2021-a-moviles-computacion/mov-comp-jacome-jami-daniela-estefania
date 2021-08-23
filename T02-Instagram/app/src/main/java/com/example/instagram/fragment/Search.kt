package com.example.instagram.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagram.R
import com.example.instagram.adaptor.SearchAdaptor
import com.example.instagram.entrenador.PhotosEntrenador

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

var recyclerSPhotos : RecyclerView? = null

/**
 * A simple [Fragment] subclass.
 * Use the [Search.newInstance] factory method to
 * create an instance of this fragment.
 */
class Search : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentSearch = inflater.inflate(R.layout.fragment_search, container, false)
        recyclerSPhotos = fragmentSearch.findViewById(R.id.recycler_view_notifications)
        // Inflate the layout for this fragment
        return fragmentSearch
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Search.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Search().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val listaPhotos = arrayListOf<PhotosEntrenador>()
        listaPhotos.add(PhotosEntrenador(R.drawable.imagen7))
        listaPhotos.add(PhotosEntrenador(R.drawable.imagen6))
        listaPhotos.add(PhotosEntrenador(R.drawable.imagen8))
        listaPhotos.add(PhotosEntrenador(R.drawable.imagen10))
        listaPhotos.add(PhotosEntrenador(R.drawable.imagen11))
        listaPhotos.add(PhotosEntrenador(R.drawable.imagen10))
        listaPhotos.add(PhotosEntrenador(R.drawable.imagen8))
        listaPhotos.add(PhotosEntrenador(R.drawable.imagen6))
        listaPhotos.add(PhotosEntrenador(R.drawable.imagen7))
        listaPhotos.add(PhotosEntrenador(R.drawable.imagen8))
        listaPhotos.add(PhotosEntrenador(R.drawable.imagen11))
        listaPhotos.add(PhotosEntrenador(R.drawable.imagen10))


        recyclerSPhotos(listaPhotos,this, recyclerSPhotos!!,
            SearchAdaptor(this,listaPhotos, recyclerSPhotos!!)
        )

    }

    fun recyclerSPhotos(lista: List<*>, actividad: Search, recyclerView: RecyclerView, adaptador:RecyclerView.Adapter<*>){
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator= androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        // recyclerView.layoutManager = LinearLayoutManager(actividad.context, LinearLayoutManager.HORIZONTAL, false)
        adaptador.notifyDataSetChanged()
    }
}