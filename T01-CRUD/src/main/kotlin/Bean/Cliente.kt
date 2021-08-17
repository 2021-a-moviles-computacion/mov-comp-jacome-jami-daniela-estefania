package Bean
import java.util.*

class Cliente(
    //constructor primario
    cedula: String?,
    nombre: String?,
    apellido: String?,
    correo:String?,
    fechaNacimiento: String?,
    status: Boolean?,
    telefono: String?,
    idProveedor: String?
) {
    var cedula_cli = cedula
    var nombre_cli = nombre
    var apellido_cli = apellido
    var correo_cli = correo
    var fechaNacimiento_cli = fechaNacimiento
    var status_cli = status
    var telefono_cli = telefono
    var idProveedor = idProveedor

    override fun toString(): String {
        return (cedula_cli+"-"+nombre_cli+"-"+apellido_cli+"-"+correo_cli+"-"
                +"-"+fechaNacimiento_cli+"-"+status_cli+"-"+telefono_cli
                +"-"+idProveedor)
    }


}