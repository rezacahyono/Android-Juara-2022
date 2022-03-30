package com.rchyno.diceroller

import com.rchyno.diceroller.model.Dice
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun rollDice(){
        val dice = Dice(6)
        val rollResult = dice.roll()
        assertTrue("the value of rollResult was not berween 1 and 6", rollResult in 1..6)
    }
}