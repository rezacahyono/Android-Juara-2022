package com.rchyn.desserclicker.model

import androidx.annotation.DrawableRes

data class Dessert(
    @DrawableRes
    val imageResourceId: Int,
    val price: Int,
    val startProductionAmount: Int
)
