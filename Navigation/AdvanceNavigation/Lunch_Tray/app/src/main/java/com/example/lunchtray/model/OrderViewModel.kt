package com.example.lunchtray.model

import androidx.lifecycle.ViewModel
import com.example.lunchtray.data.DataSource


class OrderViewModel : ViewModel() {

    //map of menu items
    val menu = DataSource.menuItems

    //default values of price
    private var previousEntreePrice = 0.0
    private var previousSidePrice = 0.0
    private var previousAccompanimentPrice = 0.0
}