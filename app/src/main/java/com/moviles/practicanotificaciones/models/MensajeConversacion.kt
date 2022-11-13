package com.moviles.practicanotificaciones.models

class MensajeConversacion (
    var id: Int = 0,
    var fechaHora: String = "",
    var mensaje: String = "",
    var idUsrEnvio: Int = 0,
    var idUsrReceptor: Int = 0,
    var idCharla: Int = 0,
    var nombreEnvio: String = "",
    var nombreReceptor: String = ""
) {
    override fun toString(): String {
        return "MensajeConversacion(id=$id, fechaHora='$fechaHora', mensaje='$mensaje', idUsrEnvio=$idUsrEnvio, idUsrReceptor=$idUsrReceptor, idCharla=$idCharla, nombreEnvio='$nombreEnvio', nombreReceptor='$nombreReceptor')"
    }
}