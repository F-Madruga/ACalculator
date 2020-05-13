package pt.ulusofona.cm.domain.calculator

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ulusofona.cm.data.local.room.dao.OperationDao
import pt.ulusofona.cm.ui.listeners.OnHistoryChanged
import retrofit2.Retrofit

class HistoryLogic(private val storage: OperationDao, private val retrofit: Retrofit) {

    private val TAG = HistoryLogic::class.java.simpleName

    fun getAll(listener: OnHistoryChanged) {
        CoroutineScope(Dispatchers.IO).launch {
            listener.onHistoryChanged(storage.getAll())
            Log.i(TAG, "Done Getting all")
        }
    }
}