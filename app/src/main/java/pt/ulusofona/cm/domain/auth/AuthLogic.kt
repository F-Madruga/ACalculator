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

    fun authenticateUser(email: String, password: String, listener: OnAuthenticated) {
        Log.i(TAG, "AuthenticateUser")
        val service = retrofit.create(AuthService::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.login(Login(email, password))
            if (response.isSuccessful && response.body() != null) {
                response.body()?.let {
                    listener.onAuthenticateSuccess(it.email, it.token)
                }
            }
            else {
                listener.onAuthenticateFailure()
            }
        }
    }

    fun registerUser(name: String, email: String, password: String, listener: OnRegistered) {
        Log.i(TAG, "AuthenticateUser")
        val service = retrofit.create(AuthService::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.register(Register(name, email, password))
            if (response.isSuccessful && response.body() != null) {
                listener.onRegisteredSuccess()
            }
            else {
                listener.onRegisteredFailure()
            }
        }
    }
}