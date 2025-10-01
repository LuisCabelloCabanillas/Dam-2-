fun main() {
    println("Elige un número entre el 1 y el 7")

    var salida = ""
    var numElecc= readLine()!!.toInt()

    if (numElecc > 7 ){
        salida= "Debes de elegir otro número"
    } else {
        when (numElecc) {
            1 -> salida = "Lunes"
            2 -> salida = "Martes"
            3 -> salida = "Miercoles"
            4 -> salida = "Jueves"
            5 -> salida = "Viernes"
            6 -> salida = "Sábado"
            7 -> salida = "Domingo"
        }
    }
    println(salida)
}