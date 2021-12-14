package com.example.lunchtray.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.lunchtray.data.DataSource
import java.text.NumberFormat


class OrderViewModel : ViewModel() {

    //map of menu items
    val menu = DataSource.menuItems

    //default values of price
    private var previousEntreePrice = 0.0
    private var previousSidePrice = 0.0
    private var previousAccompanimentPrice = 0.0

    //default tax rate
    private val taxRate = 0.08

    //properties
    private val _entree = MutableLiveData<MenuItem?>()
    val entree: LiveData<MenuItem?> = _entree

    private val _side = MutableLiveData<MenuItem?>()
    val side: LiveData<MenuItem?> = _side

    private val _accompaniment = MutableLiveData<MenuItem?>()
    val accompaniment: LiveData<MenuItem?> = _accompaniment

    private val _subtotal = MutableLiveData(0.0)
    val subtotal: LiveData<String> = Transformations.map(_subtotal){
        NumberFormat.getCurrencyInstance().format(it)
    }

    private val _total = MutableLiveData(0.0)
    val total: LiveData<String> = Transformations.map(_total){
        NumberFormat.getCurrencyInstance().format(it)
    }

    private val _tax = MutableLiveData(0.0)
    val tax: LiveData<String> = Transformations.map(_tax){
        NumberFormat.getCurrencyInstance().format(it)
    }

    //TODO: Set Entree, Side, Accompaniment
    //TODO: if value is not null, set previous item price to the current item price
    //TODO: if subtotalValue is not null, subtract previous item price from the current subtotal
    //TODO: set the current item value to the menu item corresponding to the passed in string
    //TODO: update subtotal to reflect the price of the selected item

    fun setEntree(entree: String){

    }

    fun setSide(side: String){

    }

    fun setAccompaniment(accompaniment: String){

    }

    //TODO: Calculate subtotal, tax
    private fun udpateSubtotal(itemPrice: Double){

    }

    fun calculateTaxAndTotal(){

    }

    fun resetOrder(){}


}