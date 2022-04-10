package com.rchyn.lunchtray.ui.order.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.rchyn.lunchtray.data.DataSource
import com.rchyn.lunchtray.model.MenuItem
import java.text.NumberFormat

class OrderViewModel : ViewModel() {

    val menuItems = DataSource.menuItems

    private var previousEntreePrice = 0.0
    private var previousSidePrice = 0.0
    private var previousAccompanimentPrice = 0.0

    private val taxRate = 0.08

    private val _entree = MutableLiveData<MenuItem?>()
    val entree: LiveData<MenuItem?> = _entree

    private val _side = MutableLiveData<MenuItem?>()
    val side: LiveData<MenuItem?> = _side

    private val _accompaniment = MutableLiveData<MenuItem?>()
    val accompaniment: LiveData<MenuItem?> = _accompaniment

    private val _subtotal = MutableLiveData(0.0)
    val subtotal: LiveData<String> = Transformations.map(_subtotal) {
        NumberFormat.getCurrencyInstance().format(it)
    }

    private val _total = MutableLiveData(0.0)
    val total: LiveData<String> = Transformations.map(_total) {
        NumberFormat.getCurrencyInstance().format(it)
    }

    private val _tax = MutableLiveData(0.0)
    val tax: LiveData<String> = Transformations.map(_tax) {
        NumberFormat.getCurrencyInstance().format(it)
    }

        fun setEntree(entree: String) {
        if (_entree.value != null) {
            previousEntreePrice = _entree.value!!.price
        }

        if (_subtotal.value != null) {
            _subtotal.value = (_subtotal.value)?.minus(previousEntreePrice)
        }

        _entree.value = menuItems[entree]
        updateSubtotal(_entree.value!!.price)
    }

    fun setSide(side: String) {
        if (_side.value != null) {
            previousSidePrice = _side.value!!.price
        }

        if (_subtotal.value != null) {
            _subtotal.value = (_subtotal.value)?.minus(previousSidePrice)
        }

        _side.value = menuItems[side]
        updateSubtotal(_side.value!!.price)
    }

    fun setAccompaniment(accompaniment: String) {

        if (_accompaniment.value != null) {
            previousAccompanimentPrice = _accompaniment.value!!.price
        }

        if (_subtotal.value != null) {
            _subtotal.value = (_subtotal.value)?.minus(previousAccompanimentPrice)
        }

        _accompaniment.value = menuItems[accompaniment]
        updateSubtotal(_accompaniment.value!!.price)
    }

    private fun updateSubtotal(itemPrice: Double) {
        if (_subtotal.value != null) {
            _subtotal.value = (_subtotal.value)?.plus(itemPrice)
        } else {
            _subtotal.value = itemPrice
        }

        calculateTaxAndTotal()
    }

    private fun calculateTaxAndTotal() {
        _tax.value = _subtotal.value!! * taxRate
        _total.value = (_subtotal.value)?.plus(_tax.value!!)
    }

    fun resetOrder() {
        previousAccompanimentPrice = 0.0
        previousSidePrice = 0.0
        previousEntreePrice = 0.0
        _entree.value = null
        _side.value = null
        _accompaniment.value = null
        _total.value = 0.0
        _subtotal.value = 0.0
        _tax.value = 0.0

    }
}