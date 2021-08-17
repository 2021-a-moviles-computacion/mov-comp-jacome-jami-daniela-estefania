import CRUD.Archivo
import java.util.*
import kotlin.system.exitProcess

val archivo = Archivo()
fun main() {
    val create = "Crear"
    val read = "Leer"
    val update = "Actualizar"
    val delete = "Eliminar"
    val cli = "Cliente"
    val prov = "Proveedor"

    println(
        "------------------------------- MENU-------------------------------\n" +
                "\t 1. ${create}\n" +
                "\t 2. ${read}\n" +
                "\t 3. ${update}\n" +
                "\t 4. ${delete}\n"+
                "Seleccione una opción: "
    )
    var option: Int = readInt()
    if (option <= 4 && option >= 1){ //controla numero digitalizado
        var entidad = submenu(cli, prov)
        println("\t******************************************")
        when (entidad) {
            (cli) -> {
                if (option == 1) {
                    println("\t\t\t\t"+create.toUpperCase()+" "+cli.toUpperCase())
                    //println("crearCliente()")
                    archivo.createCli()
                } else if (option == 2) {
                    println("\t\t\t\t"+read.toUpperCase()+" "+cli.toUpperCase())
                    // println("readCliente()")
                    archivo.readCli()
                } else if (option == 3) {
                    println("\t\t\t\t"+update.toUpperCase()+" "+cli.toUpperCase())
                    // println("updateCliente()")
                    archivo.readCli()
                    println("Ingresar la cedula del Cliente:")
                    archivo.updateCliente(ReadOptionStr())
                } else if (option == 4) {
                    println("\t\t\t\t"+delete.toUpperCase()+" "+cli.toUpperCase())
                    // println("deleteCliente()")
                    archivo.readCli()
                    println("Ingresar la cedula del Cliente:")
                    archivo.deleteCliente(ReadOptionStr())
                    archivo.readCli()
                }
            }
            (prov) -> {
                if (option == 1) {
                    println("\t\t\t\t"+create.toUpperCase()+" "+prov.toUpperCase())
                    //println("crearCliente()")
                    archivo.createProv()
                } else if (option == 2) {
                    println("\t\t\t\t"+read.toUpperCase()+" "+prov.toUpperCase())
                    // println("readCliente()")
                    archivo.readProv()
                } else if (option == 3) {
                    println("\t\t\t\t"+update.toUpperCase()+" "+prov.toUpperCase())
                    // println("updateCliente()")
                    archivo.readProv()
                    println("Ingresar el RUC del Proveedor:")
                    archivo.updateProveedor(ReadOptionStr())
                } else if (option == 4) {
                    println("\t\t\t\t"+delete.toUpperCase()+" "+prov.toUpperCase())
                    // println("deleteCliente()")
                    archivo.readProv()
                    println("Ingresar el RUC del Proveedor:")
                    archivo.deleteProveedor(ReadOptionStr())
                    archivo.readProv()
                }
            }
        }
    }else{
        println("Opción no valida")
    }
    opcion()
}

fun opcion(){
    println("Desea salir\n1.Si\n2.No \nSeleccione una opción: ")
    var option: Int = readInt()
    if (option == 1){
        println("Programa cerrado")
    }else if (option == 2){
            main()
    }else{
        println("Opción no valida")
        opcion()
    }


}

fun submenu(cli: String, prov: String): String {
    println(
        "------------------------------- OPCIONES ------------------------------- \n" +
                "1. ${cli}\n" +
                "2. ${prov}\n"+
                "Seleccione una opcion: "
    )
    val option = readInt()
    return if (option == 1)
        return cli
    else if (option == 2)
        return prov
    else {
        println("Opcion no existe, volver a digitar")
        submenu(cli, prov)
    }
}

fun readInt(): Int {
    val scan = Scanner(System.`in`)
    return scan.nextLine().toInt()
}

fun ReadOptionStr(): String {
    val scan = Scanner(System.`in`)
    return scan.nextLine().toString()
}


