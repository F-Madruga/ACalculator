package pt.ulusofona.cm.data.local.entities

class User(val name:String, val email:String,  val password:String) {

    override fun toString(): String {
        return "${name} | ${email} | ${password}"
    }

}