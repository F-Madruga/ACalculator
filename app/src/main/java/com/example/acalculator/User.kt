package com.example.acalculator

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class User(val name:String, val email:String,  val password:String) : Parcelable {

    override fun toString(): String {
        return "${name} | ${email} | ${password}"
    }
}