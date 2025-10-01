fun main() {
    println("Que numero quieres escoger: ")
    var numero = readLine()!!.toInt()

    if (numero > 0) {
        println("El número es positivo ($numero)")
    } else if (numero == 0) {
        println("El número es 0")
    }else {
        println("El es negativo ($numero)")
    }

}