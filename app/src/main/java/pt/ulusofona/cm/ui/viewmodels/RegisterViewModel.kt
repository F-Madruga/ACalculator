package pt.ulusofona.cm.ui.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ulusofona.cm.data.remote.RetrofitBuilder
import pt.ulusofona.cm.domain.auth.AuthLogic
import pt.ulusofona.cm.ui.listeners.OnRegistered

class RegisterViewModel: ViewModel(), OnRegistered {

    private val authLogic: AuthLogic = AuthLogic(RetrofitBuilder.getInstance(ENDPOINT))
    private var listener: OnRegistered? = null
    var success: Boolean = false

    fun onClickSubmitRegister(name: String, email: String, password: String) {
        CoroutineScope(Dispatchers.IO).launch {
            authLogic.registerUser(name, email, password, this@RegisterViewModel)
        }
    }

    private fun notifyRegistered() {
        CoroutineScope(Dispatchers.Main).launch {
            listener?.onRegistered(success)
        }
    }

    fun registerListener(listener: OnRegistered) {
        this.listener = listener
    }

    fun unregisterListener() {
        listener = null
    }

    override fun onRegistered(success: Boolean) {
        this.success = success
        notifyRegistered()
    }
}