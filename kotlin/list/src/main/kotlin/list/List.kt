fun main() {

    /**
     * List hanya untuk dibaca.
     * maka List tidak dapat diubah
     * val colors = listOf("red","blue","green")
     * colors[1] = "yellow"
     * @error unresolved add
     *  colors[1] = "yellow"
     */

    val numbers = listOf(1, 2, 3, 4, 5, 6)

    println("List numbers : $numbers")
    println("Size numbers : ${numbers.size}")

    // Access element of the list
    println("First element numbers : ${numbers[0]}")
    println("Second element numbers : ${numbers[1]}")
    println("Last index numbers : ${numbers.size - 1}")
    println("Last element numbers : ${numbers[numbers.size - 1]}")
    println("First : ${numbers.first()}")
    println("Last : ${numbers.last()}")

    // Use the contains() method
    println("Number contains 3? ${numbers.contains(3)}")
    println("Number contains 7? ${numbers.contains(7)}")


    // use reversed() sorted() method in list
    val colors = listOf("red", "blue", "green")
    println("Colors list : $colors")

    println("Colors reversed : ${colors.reversed()}")
    println("Colors sorted ascending : ${colors.sorted()}")
    println("Colors sorted descending : ${colors.sortedDescending()}")


}