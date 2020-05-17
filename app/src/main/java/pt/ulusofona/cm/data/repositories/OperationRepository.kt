package pt.ulusofona.cm.data.repositories

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ulusofona.cm.data.local.entities.Operation
import pt.ulusofona.cm.data.local.room.dao.OperationDao
import pt.ulusofona.cm.data.remote.requests.AddOperation
import pt.ulusofona.cm.data.remote.services.OperationService
import retrofit2.Retrofit

class OperationRepository(private val local: OperationDao, private val remote: Retrofit) {

    suspend fun getOperations(token: String) : List<Operation> {
        val service = remote.create(OperationService::class.java)
        val response = service.getOperations(token)
        // Recebe resposta da API
        if (response.isSuccessful) {
            val operations = response.body()?.map { response -> response.toOperation() }?: listOf()
            // Cria uma thread para atualizar a base de dados local em paralelo
            CoroutineScope(Dispatchers.IO).launch {
                local.deleteAll()
                operations.forEach{
                    local.insert(it)
                }
            }
            // Retorna dados remotos
            return operations
        }
        // Não recebe resposta da API
        else {
            // Retorna dados locais
            return local.getAll()
        }
    }

    suspend fun deleteAllOperations(token: String) {
        local.deleteAll()
        val service = remote.create(OperationService::class.java)
        val response = service.deleteOperations(token)
        if (response.isSuccessful) {

        }
        else {

        }
    }

    suspend fun addOperation(token: String, operation: Operation) {
        local.insert(operation)
        val service = remote.create(OperationService::class.java)
        val response = service.addOperation(token, AddOperation(operation.uuid, operation.expression, operation.result))
        if (response.isSuccessful) {

        }
        else {

        }
    }
}