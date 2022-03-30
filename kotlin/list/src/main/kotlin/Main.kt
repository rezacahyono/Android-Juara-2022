fun main() {

//    val noodles = Noodles()
//    val vegetables1 = Vegetables("Cabbage", "Sprouts", "Onion")
//    val vegetables2 = Vegetables()
//    println(noodles)
//    println(vegetables1)
//    println(vegetables2)

    val ordersList = mutableListOf<Order>()

    val order1 = Order(1)
    order1.addItem(Noodles())
    ordersList.add(order1)

    val order2 = Order(2)
    order2.addItem(Noodles())
    order2.addItem(Vegetables())
    ordersList.add(order2)

    val order3 = Order(3)
    val items = listOf(Noodles(), Vegetables("Carrots", "Beans", "Celery"))
    order3.addAll(items)
    ordersList.add(order3)

    val order4 = Order(4).apply {
        addItem(Noodles())
        addItem(Vegetables("Cabbage","Onion"))
    }
    ordersList.add(order4)


    ordersList.add(
        Order(5)
            .addItem(Noodles())
            .addItem(Noodles())
            .addItem(Vegetables("Spinach")))

    for (order in ordersList){
        order.print()
        println()
    }
}


open class Item(
    val name: String,
    val price: Int
)

class Noodles : Item("Noodles", 10) {
    override fun toString(): String {
        return name
    }
}

class Vegetables(
    private vararg val toppings: String
) : Item("Vegeables", 5) {
    override fun toString(): String {
        return if (toppings.isEmpty()){
            "$name Chef's Choice"
        }else {
            name + " " + toppings.joinToString()
        }
    }
}

class Order(
    private val orderNumber: Int
){
    private val itemList = mutableListOf<Item>()

    fun addItem(newItem: Item): Order{
        itemList.add(newItem)
        return this
    }

    fun addAll(newItems: List<Item>): Order{
        itemList.addAll(newItems)
        return this
    }

    fun print(){
        println("Order #$orderNumber")
        var total = 0
        for (item in itemList){
            println("$item : $${item.price}")
            total += item.price
        }
        println("Total : $total")
    }
}