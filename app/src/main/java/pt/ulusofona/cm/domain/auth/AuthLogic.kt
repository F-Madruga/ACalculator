package pt.ulusofona.cm.domain.auth

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ulusofona.cm.data.remote.requests.Login
import pt.ulusofona.cm.data.remote.requests.Register
import pt.ulusofona.cm.data.remote.services.AuthService
import pt.ulusofona.cm.ui.listeners.OnAuthenticated
import pt.ulusofona.cm.ui.listeners.OnRegistered
import retrofit2.Retrofit

class AuthLogic(private val retrofit: Retrofit) {

    private val TAG = AuthLogic::class.java.simpleName

    suspend fun authenticateUser(email: String, password: String, listener: OnAuthenticated) {
        Log.i(TAG, "AuthenticateUser")
        val service = retrofit.create(AuthService::class.java)
        val response = service.login(Login(email, password))
        CoroutineScope(Dispatchers.IO).launch {
            if (response.isSuccessful) {
                response.body()
                Log.i(TAG, "Resposta = ${response.body().toString()}")
            }
            else {
                Log.i(TAG, "Erro = ${response.body().toString()}")
            }
        }
    }

    suspend fun registerUser(name: String, email: String, password: String, listener: OnRegistered) {
        Log.i(TAG, "AuthenticateUser")
        val service = retrofit.create(AuthService::class.java)
        val response = service.register(Register(name, email, password))
        CoroutineScope(Dispatchers.IO).launch {
            if (response.isSuccessful) {
                response.body()
                Log.i(TAG, "Resposta = ${response.body().toString()}")
                listener.onRegistered(true)
            }
            else {
                Log.i(TAG, "Erro = ${response.body().toString()}")
                listener.onRegistered(false)
            }
        }
    }
}