package pt.ulusofona.cm.data.remote.services

import pt.ulusofona.cm.data.remote.requests.AddOperation
import pt.ulusofona.cm.data.remote.responses.AddOperationResponse
import pt.ulusofona.cm.data.remote.responses.DeleteOperationResponse
import pt.ulusofona.cm.data.remote.responses.GetOperationResponse
import retrofit2.http.*
import retrofit2.Response

interface OperationService {

    @POST("operations")
    suspend fun addOperation(@Header("token") token: String, @Body body: AddOperation): Response<AddOperationResponse>

    @GET("operations")
    suspend fun getOperation(@Header("token") token: String): Response<GetOperationResponse>

    @DELETE("operations")
    suspend fun deleteOperations(@Header("token") token: String): Response<DeleteOperationResponse>
}