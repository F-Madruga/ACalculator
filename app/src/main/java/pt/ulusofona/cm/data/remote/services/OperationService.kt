package pt.ulusofona.cm.data.remote.services

import pt.ulusofona.cm.data.remote.requests.AddOperation
import pt.ulusofona.cm.data.remote.responses.AddOperationResponse
import pt.ulusofona.cm.data.remote.responses.DeleteOperationsResponse
import pt.ulusofona.cm.data.remote.responses.GetOperationsResponse
import retrofit2.http.*
import retrofit2.Response

interface OperationService {

    @POST("operations")
    suspend fun addOperation(@Header("Authorization") token: String, @Body body: AddOperation): Response<AddOperationResponse>

    @GET("operations")
    suspend fun getOperations(@Header("Authorization") token: String): Response<List<GetOperationsResponse>>

    @DELETE("operations")
    suspend fun deleteOperations(@Header("Authorization") token: String): Response<DeleteOperationsResponse>
}