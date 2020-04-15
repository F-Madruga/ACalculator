package com.example.acalculator

import net.objecthunter.exp4j.ExpressionBuilder

class CalculatorLogic {

    private val storage = ListStorage.getIntance()

    fun insertSymbol(display: String, symbol: String) : String {
        return if (display.isEmpty() && symbol == "0") symbol else display + symbol
    }

    fun performOperation(expression: String) : Double {
        val expressionBuilder = ExpressionBuilder(expression).build()
        val result = expressionBuilder.evaluate()
        storage.insert(Operation(expression, result))
        return result
    }
}