package mutablelist

fun main() {
    /**
     * Mutablelist jenis list yang dapat diubah
     *
     * penulisan :
     * val entress: MutableList<String> = mutableListOf()
     * val entress = mutableListOf<String>()
     */

    val entrees = mutableListOf<String>()

    println("Entress : $entrees")
    println("Add noddles : ${entrees.add("noodles")}")
    println("Entress : $entrees")

    println("Add spaghetti : ${entrees.add("spaghetti")}")
    println("Entrees : $entrees")

    /**
     * mutableList dapat menambahkan list langsung
     * dengan menggunakan addAll()
     */

    val moreItems = listOf("lasagna", "fettucine")
    println("Add list all : ${entrees.addAll(moreItems)}")
    println("Entress : $entrees")


    /**
     * Remove element dari mutableList
     * dengan menggunakan remove()
     */
    println("Remove lasagna : ${entrees.remove("lasagna")}")
    println("Entress : $entrees")

    println("Remove item that doesn't exist: ${entrees.remove("rice")}")
    println("Entrees: $entrees")

    println("Remove first element: ${entrees.removeAt(0)}")
    println("Entrees: $entrees")

    println("Remove all : ${entrees.clear()}")
    println("Entrees: $entrees")

    println("Checked entrees empty? : ${entrees.isEmpty()}")




}