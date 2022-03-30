package looplist

fun main() {
    /**
     * while (expression) {
     *       While the expression is true, execute this code block
     * }
     */

    val guestsPerFamilly = listOf(2, 4, 1, 3)
    var totalGuests = 0
    var index = 0
    while (index < guestsPerFamilly.size) {
        totalGuests += guestsPerFamilly[index]
        index++
    }
    println("Total Guest Count : $totalGuests")


    /**
     * for (number in numberList) {
     *      For each element in the list, execute this code block
     * }
     */

    val names = listOf("Reza", "Dimas", "Engga")
    for (name in names) {
        println("$name - Number of characters: ${name.length}")
    }


}