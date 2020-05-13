package pt.ulusofona.cm.ui.listeners

interface OnAuthenticated {

    fun onAuthenticateSuccess(email: String, token: String)

    fun onAuthenticateFailure()
}