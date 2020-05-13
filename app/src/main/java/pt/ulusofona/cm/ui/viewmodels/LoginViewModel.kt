package pt.ulusofona.cm.ui.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ulusofona.cm.data.remote.RetrofitBuilder
import pt.ulusofona.cm.domain.auth.AuthLogic
import pt.ulusofona.cm.ui.listeners.OnAuthenticated

const val ENDPOINT = "https://cm-calculadora.herokuapp.com/api/"

class LoginViewModel: ViewModel(), OnAuthenticated {

    private val authLogic: AuthLogic = AuthLogic(RetrofitBuilder.getInstance(ENDPOINT))
    private var listener: OnAuthenticated? = null

    var email: String = ""
    var token: String = ""

    fun onLogin(email: String, password: String) {
        authLogic.authenticateUser(email, password, this)
    }

    private fun notifyAuthenticationFailure() {
        CoroutineScope(Dispatchers.Main).launch {
            listener?.onAuthenticateFailure()
        }
    }

    private fun notifyAuthenticationSuccess() {
        CoroutineScope(Dispatchers.Main).launch {
            listener?.onAuthenticateSuccess(this@LoginViewModel.email, this@LoginViewModel.token)
        }
    }

    fun registerListener(listener: OnAuthenticated) {
        this.listener = listener
    }

    fun unregisterListener() {
        listener = null
    }

    override fun onAuthenticateSuccess(email: String, token: String) {
        this.email = email
        this.token = token
        notifyAuthenticationSuccess()
    }

    override fun onAuthenticateFailure() {
        notifyAuthenticationFailure()
    }
}