package com.example.moviles_computacion_2021_b

class BBaseDatosMemoria {
    companion object{


        val arregloBEntrenador = arrayListOf<BEntrenador>()
        init {
            arregloBEntrenador.add(
                BEntrenador("Adrian", "a@a.com",null)
            )
            arregloBEntrenador.add(
                BEntrenador("Daniela", "d@d.com",null)
            )
            arregloBEntrenador.add(
                BEntrenador("Estefania", "e@e.com",null)
            )
        }


    }
}