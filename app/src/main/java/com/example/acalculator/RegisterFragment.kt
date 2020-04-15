package com.example.acalculator

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import butterknife.OnClick
import kotlinx.android.synthetic.main.fragment_register.*
import org.apache.commons.codec.digest.DigestUtils

class RegisterFragment : Fragment() {

    private val TAG = RegisterFragment::class.java.simpleName
    private val users: MutableList<User> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_register, container, false)
        ButterKnife.bind(this, view)
        users.clear()
        users.addAll(activity?.intent?.getParcelableArrayListExtra<User>(EXTRA_USERS)?.toMutableList()?:mutableListOf())
        Log.i(TAG, "users = ${users.toString()}")
        return view
    }

    @OnClick(R.id.button_submit)
    fun onClickSubmit(view: View) {
        Log.i(TAG, "Press submit")
        var valid = true
        if (username.text.toString() == "") {
            username.setError("This field can't be empty")
            valid = false
        }
        if (password.text.toString() == "") {
            password.setError("This field can't be empty")
            valid = false
        }
        if (confirm_password.text.toString() == "") {
            confirm_password.setError("This field can't be empty")
            valid = false
        }
        if (email.text.toString() == "") {
            email.setError("This field can't be empty")
            valid = false
        }
        if (valid) {
            for (user in users) {
                Log.i(TAG, "Press submit")
                if (user.name == username.text.toString() || user.email == email.text.toString() || password.text.toString() != confirm_password.text.toString()) {
                    Log.i(TAG, "Invalid")
                    valid = false
                    if (user.name == username.text.toString()) {
                        Log.i(TAG, "User")
                        username.setError("Username already exists")
                    }
                    if (user.email == email.text.toString()) {
                        Log.i(TAG, "Email")
                        email.setError("Email address already used")
                    }
                    if (password.text.toString() != confirm_password.text.toString()) {
                        Log.i(TAG, "Pass")
                        confirm_password.setError("This field must be equal to password field")
                    }
                }
            }
        }
        if (valid) {
            Log.i(TAG, "Valid")
            users.add(User(username.text.toString(), email.text.toString(), DigestUtils.sha256Hex(password.text.toString())))
            val intent = Intent(context, LoginActivity::class.java)
            intent.apply { putParcelableArrayListExtra(EXTRA_USERS, ArrayList(users)) }
            startActivity(intent)
        }
    }
}
