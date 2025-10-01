fun saludar (nombre: String = "invitado") {
    println("Hola " + nombre)
}

fun main() {
    saludar("Luis")
    saludar()
}