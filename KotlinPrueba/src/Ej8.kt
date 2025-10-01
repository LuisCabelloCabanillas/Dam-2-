fun main() {
    val colores = listOf("Rojo", "Verde", "Azul", "Morado", "Amarillo")

    println(colores.size)

    for (color in colores){
        println(color)
    }

    val colores2 = mutableListOf("Rojo", "Verde", "Azul")

    colores2.add("Morado")
    colores2.add("Amarillo")
    println(colores2)

    colores2.remove("Amarillo")
    println(colores2)
}