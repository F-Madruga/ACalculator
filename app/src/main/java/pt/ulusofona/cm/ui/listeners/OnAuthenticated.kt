package pt.ulusofona.cm.ui.listeners

interface OnAuthenticated {

    fun onAuthenticated(email: String, token: String)
}