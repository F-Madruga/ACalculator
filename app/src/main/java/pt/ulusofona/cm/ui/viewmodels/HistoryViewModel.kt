package pt.ulusofona.cm.ui.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ulusofona.cm.data.local.entities.Operation
import pt.ulusofona.cm.data.local.room.CalculatorDatabase
import pt.ulusofona.cm.data.remote.RetrofitBuilder
import pt.ulusofona.cm.domain.calculator.HistoryLogic
import pt.ulusofona.cm.ui.listeners.OnHistoryChanged

class HistoryViewModel(application: Application) : AndroidViewModel(application), OnHistoryChanged {

    private val TAG = HistoryViewModel::class.java.simpleName

    private var listener: OnHistoryChanged? = null

    private val storage = CalculatorDatabase.getInstance(application).operationDao()
    private val historyLogic = HistoryLogic(storage, RetrofitBuilder.getInstance(ENDPOINT))

    var history: List<Operation> = listOf()

    override fun onHistoryChanged(history: List<Operation>) {
        Log.i(TAG, "OnHistoryChanged")
        this.history = history
        notifyOnHistoryChanged()
    }

    private fun notifyOnHistoryChanged() {
        Log.i(TAG, "NotifyOnDisplayChanged")
        CoroutineScope(Dispatchers.Main).launch {
            listener?.onHistoryChanged(history)
            Log.i(TAG, "Done Updating List")
        }
    }

    fun registerListener(listener: OnHistoryChanged?) {
        Log.i(TAG, "RegisterListener")
        this.listener = listener
        historyLogic.getAll(this)
    }

    fun unregisterListener() {
        Log.i(TAG, "UnregisterListener")
        listener = null
    }

    fun getAll() {
        historyLogic.getAll(this)
        Log.i(TAG, "Done Get List")
    }
}