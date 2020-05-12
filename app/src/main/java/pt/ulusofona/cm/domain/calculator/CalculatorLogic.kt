package pt.ulusofona.cm.domain.calculator

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.objecthunter.exp4j.ExpressionBuilder
import pt.ulusofona.cm.data.local.entities.Operation
import pt.ulusofona.cm.data.local.room.dao.OperationDao
import pt.ulusofona.cm.ui.listeners.OnHistoryChanged

class CalculatorLogic(private val storage: OperationDao) {

    private val TAG = CalculatorLogic::class.java.simpleName

    fun insertSymbol(display: String, symbol: String) : String {
        Log.i(TAG, "InsertSymbol")
        return if (display.isEmpty()) symbol else display + symbol
    }

    fun getAll(listener: OnHistoryChanged) {
        CoroutineScope(Dispatchers.IO).launch {
            listener.onHistoryChanged(storage.getAll())
        }
    }

    fun clear(display: String) : String {
        Log.i(TAG, "Clear")
        return "0"
    }

    fun deleteSymbol(display: String) : String {
        Log.i(TAG, "DeleteSymbol")
        return if(display.length == 1) "0" else display.dropLast(1)
    }

    fun performOperation(expression: String, listener: OnHistoryChanged) : Double {
        Log.i(TAG, "PerformOperation")
        val expressionBuilder = ExpressionBuilder(expression).build()
        val result = expressionBuilder.evaluate()
        CoroutineScope(Dispatchers.IO).launch {
            storage.insert(Operation(expression, result))
            listener.onHistoryChanged(storage.getAll())
        }
        return result
    }
}