fun main(){
    var nombre = "Luis"
    var edad = 18
    var multilinea = """Hola a todos
        |soy nuevo en
        |kotlin
    """.trimMargin()

    println("Mi nomber es $nombre y tengo $edad años")
    println(multilinea)
}