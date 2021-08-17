package CRUD

import Bean.Cliente
import Bean.Proveedor
import java.io.*
import java.util.*
import kotlin.collections.ArrayList


class Archivo {

    var dirCliente =
        "C:\\Users\\Danny\\OneDrive - Escuela Politécnica Nacional\\Universidad\\2021 A\\Aplicaciones Móviles\\Git\\mov-comp-jacome-jami-daniela-estefania\\T01-CRUD\\src\\main\\resources\\clientes.txt"
    var dirProveedor =
        "C:\\Users\\Danny\\OneDrive - Escuela Politécnica Nacional\\Universidad\\2021 A\\Aplicaciones Móviles\\Git\\mov-comp-jacome-jami-daniela-estefania\\T01-CRUD\\src\\main\\resources\\proveedores.txt"
    var dirNew =
        "C:\\Users\\Danny\\OneDrive - Escuela Politécnica Nacional\\Universidad\\2021 A\\Aplicaciones Móviles\\Git\\mov-comp-jacome-jami-daniela-estefania\\T01-CRUD\\src\\main\\resources\\newFile.txt"

    //referencias: https://programmerclick.com/article/2605677600/

    //CLIENTE
    fun informationCli(): String {
        var cliente: String = ""
        print("Ingrese los datos solicitados")
        println("Cedula")
        cliente += ReadOption() + "-"
        println("Nombre")
        cliente += ReadOption() + "-"
        println("Apellido")
        cliente += ReadOption() + "-"
        println("Correo")
        cliente += ReadOption() + "-"
        println("Fecha Nacimiento")
        cliente += ReadOption()
        println("Status")
        cliente += "-" + ReadOption() + "-"
        println("Telefono")
        cliente += ReadOption() + "-"
        println("idProveedor")
        cliente += ReadOption() + "-"
        return cliente
    }
    //  Create
    fun createCli(){
        val informatioCli: String = informationCli()
        println("Nuevo Cliente: " + informatioCli)
        writeCli(informatioCli)
        readCli()
        println("Escrito")
    }

    fun writeCli(cliente: String) {
        File(dirCliente).appendText(cliente.toString())
    }

    //  Read
    fun readCli() {
        val file = File(dirCliente).readLines().forEach(::println)
    }

    //  Update
    fun updateCliente(cedula: String) {
        val cliente = buscarClienteCedula(cedula)

        if (cliente != null) {
            println(cliente.toString())
            println("Nombre")
            cliente.nombre_cli = ReadOption()
            println("Apellido")
            cliente.apellido_cli = ReadOption()
            println("Correo")
           cliente.correo_cli  = ReadOption()
            println("Fecha Nacimiento")
            cliente.fechaNacimiento_cli  = ReadOption()
            println("Status")
            cliente.status_cli = ReadOption().toBoolean()
            println("Telefono")
            cliente.telefono_cli = ReadOption()
            println("idProveedor")
            cliente.idProveedor = ReadOption()
        }
        println("Nuevo Cliente: " + cliente.toString())
        updateFile(dirCliente, buscarClienteCedula(cedula)!!.toString(), cliente.toString())
        println("Cliente Actualizado!!")
    }

    //  Delete
    fun deleteCliente(cedula: String) {
        val cliente = buscarClienteCedula(cedula)
        println(cliente)
        if (cliente != null) {
            deleteFile(dirCliente, cliente.toString())
            println("Cliente Eliminado!!")
        } else {
            println("No existe el Cliente")
        }
    }

    fun buscarClientes(): ArrayList<Cliente> {
        val clientes = ArrayList<Cliente>()
        readFile(dirCliente).forEach {
            clientes.add(Cliente(it[0], it[1], it[2], it[3], it[4], it[5].toBoolean(), it[6], it[7]))
        }
        return clientes
    }

    fun buscarClienteCedula(cedula: String): Cliente? {
        val clientes: ArrayList<Cliente> = buscarClientes()
            .filter {
                return@filter (it.cedula_cli == cedula)
            } as ArrayList<Cliente>

        return if (clientes.size == 0) {
            null
        } else {
            clientes[0]
        }
    }

    //PROVEEDOR

    fun informationProv(): String {
        var proveedor: String = ""
        print("Ingrese los datos solicitados")
        println("RUC")
        proveedor += ReadOption() + "-"
        println("Nombre")
        proveedor += ReadOption() + "-"
        println("Ciudad")
        proveedor += ReadOption() + "-"
        println("Correo")
        proveedor += ReadOption() + "-"
        println("Telefono")
        proveedor += ReadOption()
        return proveedor
    }



    //  Create
    fun createProv(){
        val informatioProv: String = informationProv()
        println("Nuevo Cliente: " + informatioProv)
        writeProv(informatioProv)
        readProv()
        println("Escrito")
    }

    fun writeProv(proveedor: String) {
        File(dirProveedor).appendText(proveedor.toString())
    }

    //  Read
    fun readProv() {
        val file = File(dirProveedor).readLines().forEach(::println)
    }

    //  Update
    fun updateProveedor(ruc: String) {
        val proveedor = buscarProveedorRUC(ruc)

        if (proveedor != null) {
            println(proveedor.toString())
            println("Nombre")
            proveedor.nombre_prov=ReadOption()
            println("Ciudad")
            proveedor.ciudad_prov=ReadOption()
            println("Correo")
            proveedor.correo_prov=ReadOption()
            println("Telefono")
            proveedor.telefono_prov=ReadOption().toInt()
        }
        println("Nuevo Proveedor: " + proveedor.toString())
        updateFile(dirProveedor, buscarProveedorRUC(ruc)!!.toString(), proveedor.toString())
        println("Proveedor Actualizado!!")

    }

    //  Delete
    fun deleteProveedor(ruc: String) {
        val proveedor = buscarProveedorRUC(ruc)
        println(proveedor)
        if (proveedor != null) {
            deleteFile(dirProveedor, proveedor.toString())
            println("Proveedor Eliminado!!")
        } else {
            println("No existe el Proveedor")
        }
    }

    fun buscarProveedores(): ArrayList<Proveedor> {
        val proveedores = ArrayList<Proveedor>()
        readFile(dirProveedor).forEach {
            proveedores.add(Proveedor(it[0], it[1], it[2], it[3], it[4].toInt()))
        }
        return proveedores
    }

    fun buscarProveedorRUC(ruc: String): Proveedor? {
        val proveedores: ArrayList<Proveedor> = buscarProveedores()
            .filter {
                return@filter (it.ruc_prov == ruc)
            } as ArrayList<Proveedor>

        return if (proveedores.size == 0) {
            null
        } else {
            proveedores[0]
        }
    }

    private fun updateFile(
        path: String,
        antes: String,
        despues: String
    ) {
        val fl: Scanner
        val wr: PrintWriter
        val f1 = File(dirNew)
        f1.createNewFile()
        val f2 = File(path)
        try {
            fl = Scanner(f2)//original
            wr = PrintWriter(f1)//copia
            while (fl.hasNext()) {
                val line = fl.nextLine()
                var line2 = line.split("-")
                var antes2 = antes.split("-")
                if (line2.get(0).equals(antes2.get(0))) {
                    wr.write(despues)
                    wr.write("\n")
                } else {
                    wr.write(line)
                    wr.write("\n")
                }

            }
            fl.close()
            wr.close()
            f2.delete()
            (f1).renameTo(f2)
        } catch (ex: FileNotFoundException) {
            println("Error")
        }


    }

    fun ReadOption(): String {
        val scan = Scanner(System.`in`)
        return scan.nextLine().toString()
    }

    private fun deleteFile(
        direccion: String,
        lineToRemove: String
    ) {

        val fl: Scanner
        val wr: PrintWriter
        val f1 = File(dirNew)

        val f2 = File(direccion)
        try {
            fl = Scanner(f2)//original
            wr = PrintWriter(f1)//copia
            while (fl.hasNext()) {
                val line = fl.nextLine()
                println("linea" + line)
                var line2 = line.split("-")
                println("linea 2" + line2)
                var lineToRemove2 = lineToRemove.split("-")
                println("lineToRemove2 " + lineToRemove2)
                if (!line2.get(0).equals(lineToRemove2.get(0))) {
                    wr.write(line)
                    wr.write("\n")
                }
            }
            fl.close()
            wr.close()
            f2.delete()
            (f1).renameTo(f2)

        } catch (ex: FileNotFoundException) {
            //Logger.getLogger(Test::class.java.getName()).log(Level.SEVERE, null, ex)
        }


    }

    fun readFile(directorio: String ): ArrayList<ArrayList<String>>
    {
        val entidad = ArrayList<String>()
        val entidades = arrayListOf(ArrayList<String>())

        try {
            val read = Scanner(File(directorio))
            read.nextLine()
            while (read.hasNextLine()) {
                val arg = StringTokenizer(read.nextLine(), "-")
                while (arg.hasMoreTokens()) {
                    entidad.add(arg.nextToken())
                }
                entidades.add(entidad.clone() as ArrayList<String>)
                entidad.clear()
            }
            read.close()
        } catch (e: FileNotFoundException) {
            println("El archivo no fue leído.")
            e.printStackTrace()
        }
        entidades.removeAt(0)
        return entidades
    }

}