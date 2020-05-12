package pt.ulusofona.cm.data.local.list

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pt.ulusofona.cm.data.local.entities.Operation

class ListStorage private constructor() {

    private val TAG = ListStorage::class.java.simpleName

    private val storage = mutableListOf<Operation>()

    companion object {

        private var instance: ListStorage? = null

        fun getIntance(): ListStorage {
            synchronized(this) {
                if (instance == null) {
                    instance = ListStorage()
                }
                return instance as ListStorage
            }
        }
    }

    suspend fun insert(operation: Operation) {
        Log.i(TAG, "Insert")
        withContext(Dispatchers.IO) {
            //Thread.sleep(3000)
            storage.add(operation)
        }
    }

    suspend fun getAll(): List<Operation> {
        Log.i(TAG, "getAll")
        return withContext(Dispatchers.IO) {
            storage.toList()
        }
    }

}