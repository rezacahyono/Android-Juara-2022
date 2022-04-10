package com.rchyn.lunchtray.model

import com.rchyn.lunchtray.constant.ItemType
import java.text.NumberFormat

data class MenuItem(
    val name: String,
    val description: String,
    val price: Double,
    val type: ItemType
) {
    fun getFormattedPrice(): String {
        return NumberFormat.getCurrencyInstance().format(price)
    }
}
