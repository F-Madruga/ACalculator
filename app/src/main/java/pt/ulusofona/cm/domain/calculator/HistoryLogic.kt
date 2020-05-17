package pt.ulusofona.cm.domain.calculator

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ulusofona.cm.data.local.room.dao.OperationDao
import pt.ulusofona.cm.data.remote.services.OperationService
import pt.ulusofona.cm.data.repositories.OperationRepository
import pt.ulusofona.cm.ui.listeners.OnHistoryChanged
import retrofit2.Retrofit

class HistoryLogic(private val repository: OperationRepository) {

    private val TAG = HistoryLogic::class.java.simpleName

    fun getAll(listener: OnHistoryChanged, token: String) {
        Log.i(TAG, "GetAll")
        CoroutineScope(Dispatchers.IO).launch {
            listener.onHistoryChanged(repository.getOperations(token))
        }
    }

    fun deleteAll(listener: OnHistoryChanged, token: String) {
        Log.i(TAG, "DeleteAll")
        CoroutineScope(Dispatchers.IO).launch {
            repository.deleteAllOperations(token)
            listener.onHistoryChanged(listOf())
        }
    }
}