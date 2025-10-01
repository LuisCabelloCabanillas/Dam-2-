fun temperatura(temperatura:Int): Boolean {
    if (temperatura > 25){
        return true
    } else {
        return false
    }

}

fun main() {
    println(temperatura(24))

    println(temperatura(45))
}