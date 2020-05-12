package pt.ulusofona.cm.ui.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ulusofona.cm.data.local.entities.Operation
import pt.ulusofona.cm.data.local.room.CalculatorDatabase
import pt.ulusofona.cm.domain.calculator.CalculatorLogic
import pt.ulusofona.cm.ui.listeners.OnDisplayChanged
import pt.ulusofona.cm.ui.listeners.OnHistoryChanged

class CalculatorViewModel(application: Application) : AndroidViewModel(application), OnHistoryChanged {

    private val TAG = CalculatorViewModel::class.java.simpleName

    private var displayListener: OnDisplayChanged? = null
    private var historyListener: OnHistoryChanged? = null

    private val storage = CalculatorDatabase.getInstance(application).operationDao()
    private val calculatorLogic = CalculatorLogic(storage)

    var display: String = ""
    var history: List<Operation> = listOf()

    private fun notifyOnDisplayChanged() {
        Log.i(TAG, "NotifyOnDisplayChanged")
        displayListener?.onDisplayChanged(display)
    }

    fun registerDisplayListener(listener: OnDisplayChanged?) {
        Log.i(TAG, "RegisterListener")
        this.displayListener = listener
        listener?.onDisplayChanged(display)
    }

    private fun notifyOnHistoryChanged() {
        Log.i(TAG, "NotifyOnDisplayChanged")
        CoroutineScope(Dispatchers.Main).launch {
            historyListener?.onHistoryChanged(history)
        }
    }

    fun registerHistoryListener(listener: OnHistoryChanged?) {
        Log.i(TAG, "RegisterListener")
        this.historyListener = listener
        calculatorLogic.getAll(this)
    }

    fun unregisterListener() {
        Log.i(TAG, "UnregisterListener")
        displayListener = null
    }

    override fun onHistoryChanged(history: List<Operation>) {
        this.history = history
        notifyOnHistoryChanged()
    }

    fun onClickEquals() {
        Log.i(TAG, "OnClickEquals")
        val result = calculatorLogic.performOperation(display, this)
        display = result.toString()
        notifyOnDisplayChanged()
    }

    fun onClickSymbol(symbol: String) {
        Log.i(TAG, "OnClickSymbol")
        display = calculatorLogic.insertSymbol(display, symbol)
        notifyOnDisplayChanged()
    }

    fun onClickDelete() {
        Log.i(TAG, "OnClickDelete")
        display = calculatorLogic.deleteSymbol(display)
        notifyOnDisplayChanged()
    }

    fun onClickClear() {
        Log.i(TAG, "OnClickClear")
        display = calculatorLogic.clear(display)
        notifyOnDisplayChanged()
    }
}