package pt.ulusofona.cm.data.remote.responses

import com.google.gson.annotations.SerializedName
import pt.ulusofona.cm.data.local.entities.Operation

data class GetOperationsResponse(@SerializedName("uuid") val uuid: String, @SerializedName("expression") val expression: String, @SerializedName("result") val result: Double) {

    fun toOperation(): Operation {
        val operation = Operation(this.expression, this.result)
        operation.uuid = this.uuid
        return operation
    }

    override fun toString(): String {
        return "$uuid | $expression | $result"
    }
}