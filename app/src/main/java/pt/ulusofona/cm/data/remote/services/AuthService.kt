package pt.ulusofona.cm.data.remote.services

import pt.ulusofona.cm.data.remote.requests.Login
import pt.ulusofona.cm.data.remote.requests.Register
import pt.ulusofona.cm.data.remote.responses.LoginResponse
import pt.ulusofona.cm.data.remote.responses.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("users/login")
    suspend fun login(@Body body: Login): Response<LoginResponse>

    @POST("users/register")
    suspend fun register(@Body body: Register): Response<RegisterResponse>
}