package pt.ulusofona.cm.data.remote.responses

import com.google.gson.annotations.SerializedName

data class LoginResponse(@SerializedName("email") val email: String, @SerializedName("token") val token: String) {

    override fun toString(): String {
        return "$email | $token"
    }
}