fun main() {
    val array= arrayOf(1,2,3,4,5)
    val arrayInt = intArrayOf(1,2,3,4,5)

    val union = arrayInt.toList()+array.toList()

    println(union)

}