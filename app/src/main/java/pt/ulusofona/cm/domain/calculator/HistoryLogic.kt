package pt.ulusofona.cm.domain.calculator

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ulusofona.cm.data.local.room.dao.OperationDao
import pt.ulusofona.cm.data.remote.services.OperationService
import pt.ulusofona.cm.ui.listeners.OnHistoryChanged
import retrofit2.Retrofit

class HistoryLogic(private val storage: OperationDao, private val retrofit: Retrofit) {

    private val TAG = HistoryLogic::class.java.simpleName

    fun getAll(listener: OnHistoryChanged, token: String) {
        Log.i(TAG, "GetAll")
        val service = retrofit.create(OperationService::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getOperations(token)
            if (response.isSuccessful) {
                listener.onHistoryChanged(response.body()?.map { response -> response.toOperation() }?: listOf())
            }
            else {
                Log.i(TAG, "Error")
            }
            //Get from room
            //listener.onHistoryChanged(storage.getAll())
        }
    }

    fun deleteAll(listener: OnHistoryChanged, token: String) {
        Log.i(TAG, "DeleteAll")
        val service = retrofit.create(OperationService::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.deleteOperations(token)
            if (response.isSuccessful) {
                Log.i(TAG, response.body().toString())
                listener.onHistoryChanged(listOf())
            }
            else {
                Log.i(TAG, "Error")
            }
        }
    }
}