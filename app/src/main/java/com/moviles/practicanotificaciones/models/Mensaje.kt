package com.moviles.practicanotificaciones.models

class Mensaje (
    var idUsuarioEnvio: Int = 0,
    var idUsuarioReceptor: Int = 0,
    var mensaje: String = "",
    var idCharla: Int = 0
) {
    override fun toString(): String {
        return "Mensaje(isUsuarioEnvio=$idUsuarioEnvio, idUsuarioReceptor=$idUsuarioReceptor, mensaje='$mensaje', idCharla=$idCharla)"
    }
}