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
import kotlinx.android.synthetic.main.drawer_header.*
import kotlinx.android.synthetic.main.fragment_login.*
import org.apache.commons.codec.digest.DigestUtils

const val EXTRA_USERS = "com.example.acalculator.USERS"
const val EXTRA_USERNAME = "com.example.acalculator.USERNAME"
const val EXTRA_EMAIL = "com.example.acalculator.EMAIL"

class LoginFragment : Fragment() {

    private val TAG = LoginFragment::class.java.simpleName
    private val users: MutableList<User> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        ButterKnife.bind(this, view)
        users.clear()
        users.addAll(activity?.intent?.getParcelableArrayListExtra<User>(EXTRA_USERS)?.toMutableList()?:mutableListOf())
        users.add(User("Jorge", "jorge@email.com", DigestUtils.sha256Hex("1234")))
        Log.i(TAG, "users = ${users.toString()}"
        )
        return view
    }

    @OnClick(R.id.button_register)
    fun onClickRegister(view: View) {
        val intent = Intent(context, RegisterActivity::class.java)
        intent.apply { putParcelableArrayListExtra(EXTRA_USERS, ArrayList(users)) }
        startActivity(intent)
    }

    @OnClick(R.id.button_login)
    fun onClickLogin(view: View) {
        var authenticate = false
        for (user in users) {
            if (user.name == username.text.toString()) {
                val passwordHash: String = DigestUtils.sha256Hex(password.text.toString())
                if (user.password == passwordHash) {
                    authenticate = true
                    val intent = Intent(context, MainActivity::class.java)
                    intent.apply {
                        putExtra(EXTRA_USERNAME, user.name)
                        putExtra(EXTRA_EMAIL, user.email)
                    }
                    startActivity(intent)
                }
                break
            }
        }
        if (!authenticate) {
            username.setError("Username or password is wrong")
            password.setError("Username or password is wrong")
        }
    }
}
