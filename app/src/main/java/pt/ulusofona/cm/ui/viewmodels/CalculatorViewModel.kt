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
import pt.ulusofona.cm.data.remote.RetrofitBuilder
import pt.ulusofona.cm.data.repositories.OperationRepository
import pt.ulusofona.cm.domain.calculator.CalculatorLogic
import pt.ulusofona.cm.ui.listeners.OnDisplayChanged
import pt.ulusofona.cm.ui.listeners.OnHistoryChanged

class CalculatorViewModel(application: Application) : AndroidViewModel(application), OnDisplayChanged {

    private val TAG = CalculatorViewModel::class.java.simpleName

    private var listener: OnDisplayChanged? = null

    private val repository: OperationRepository = OperationRepository(CalculatorDatabase.getInstance(application).operationDao(), RetrofitBuilder.getInstance(ENDPOINT))
    private val calculatorLogic = CalculatorLogic(repository)

    var display: String = ""

    private fun notifyOnDisplayChanged() {
        Log.i(TAG, "NotifyOnDisplayChanged")
        CoroutineScope(Dispatchers.Main).launch {
            listener?.onDisplayChanged(display)
        }
    }

    override fun onDisplayChanged(value: String?) {
        Log.i(TAG, "OnDisplayChanged")
        this.display = value?:""
        notifyOnDisplayChanged()
    }

    override fun onAddOperation() {
        Log.i(TAG, "OnAddOperation")
        CoroutineScope(Dispatchers.Main).launch {
            listener?.onAddOperation()
        }
    }

    fun registerListener(listener: OnDisplayChanged?) {
        Log.i(TAG, "RegisterListener")
        this.listener = listener
        listener?.onDisplayChanged(display)
    }

    fun unregisterListener() {
        Log.i(TAG, "UnregisterListener")
        listener = null
    }

    fun onClickEquals(token: String) {
        Log.i(TAG, "OnClickEquals")
        calculatorLogic.performOperation(display, this, token)
    }

    fun onClickSymbol(symbol: String) {
        Log.i(TAG, "OnClickSymbol")
        calculatorLogic.insertSymbol(display, symbol, this)
    }

    fun onClickDelete() {
        Log.i(TAG, "OnClickDelete")
        calculatorLogic.deleteSymbol(display, this)
    }

    fun onClickClear() {
        Log.i(TAG, "OnClickClear")
        calculatorLogic.clear(display, this)
    }
}