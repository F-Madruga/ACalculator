package pt.ulusofona.cm.data.remote.responses

import com.google.gson.annotations.SerializedName

data class GetOperationResponse(@SerializedName("uuid") val uuid: String, @SerializedName("expression") val expression: String, @SerializedName("result") val result: Double) {

    override fun toString(): String {
        return "$uuid | $expression | $result"
    }
}