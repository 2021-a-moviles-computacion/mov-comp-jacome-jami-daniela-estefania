package com.example.instagram.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagram.R
import com.example.instagram.adaptor.NotificationAdaptor
import com.example.instagram.entrenador.NotificationEntrenador

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

var recyclerVNotification : RecyclerView? = null

/**
 * A simple [Fragment] subclass.
 * Use the [home.newInstance] factory method to
 * create an instance of this fragment.
 */
class Notification : Fragment() {
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
        val fragmentHome = inflater.inflate(R.layout.fragment_notifications, container, false)
        recyclerVNotification = fragmentHome.findViewById(R.id.recycler_view_notifications)
        // Inflate the layout for this fragment
        return fragmentHome
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment home.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Home().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val listaNotifications = arrayListOf<NotificationEntrenador>()
        listaNotifications.add(NotificationEntrenador("danny","started following you"))
        listaNotifications.add(NotificationEntrenador("estefania","tagged you in a post"))
        listaNotifications.add(NotificationEntrenador("alejandro","liked your photo"))
        listaNotifications.add(NotificationEntrenador("pamela","started following you"))


        recyclerVNotification(listaNotifications,this, recyclerVNotification!!,
            NotificationAdaptor(this,listaNotifications, recyclerVNotification!!)
        )
       // recyclerVPost(listaPosts,this, recyclerVPost!!,PostAdaptor(this,listaPosts,recyclerVPost!!))

    }

    private fun recyclerVNotification(lista: ArrayList<NotificationEntrenador>, actividad: Notification, recyclerView: RecyclerView, adaptador: NotificationAdaptor) {

    }

    fun recyclerVNotification(lista: List<*>, actividad: Notification, recyclerView: RecyclerView, adaptador:RecyclerView.Adapter<*>){
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator= androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = GridLayoutManager(context, 1)
         //recyclerView.layoutManager = LinearLayoutManager(actividad.context, LinearLayoutManager.HORIZONTAL, false)
        adaptador.notifyDataSetChanged()
    }

}
