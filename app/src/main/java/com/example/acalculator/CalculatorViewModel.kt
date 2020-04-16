package com.example.acalculator

import androidx.lifecycle.ViewModel

class CalculatorViewModel : ViewModel() {
    private val TAG = CalculatorViewModel::class.java.simpleName

    private var listener: OnDisplayChanged? = null

    private val calculatorLogic = CalculatorLogic()
    var display: String = ""
    var historic:MutableList<Operation> = mutableListOf<Operation>(Operation("1+1", 2.0))

    private fun notifyOnDisplayChanged() {
        listener?.onDisplayChanged(display)
    }

    fun registerListener(listener: OnDisplayChanged) {
        this.listener = listener
        listener.onDisplayChanged(display)
    }

    fun unregisterListener() {
        listener = null
    }

    fun onClickSymbol(symbol: String) {
        display = calculatorLogic.insertSymbol(display, symbol)
        notifyOnDisplayChanged()
    }

    fun onClickEquals() {
        val result = calculatorLogic.performOperation(display)
        display = result.toString()
        //historic = calculatorLogic.getAll().toMutableList()
        notifyOnDisplayChanged()
    }

    fun historic(): MutableList<Operation> {
        historic = calculatorLogic.getAll().toMutableList()
        return historic
    }
}