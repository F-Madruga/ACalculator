package pt.ulusofona.cm.ui.fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import butterknife.ButterKnife
import butterknife.OnClick
import kotlinx.android.synthetic.main.fragment_login.*
import pt.ulusofona.cm.R
import pt.ulusofona.cm.ui.activities.MainActivity
import pt.ulusofona.cm.ui.listeners.OnAuthenticated
import pt.ulusofona.cm.ui.utils.NavigationManager
import pt.ulusofona.cm.ui.viewmodels.LoginViewModel
import pt.ulusofona.cm.ui.viewmodels.RegisterViewModel

class LoginFragment : Fragment(), OnAuthenticated {

    private val TAG = LoginFragment::class.java.simpleName
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.i(TAG, "OnCreateView")
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        ButterKnife.bind(this, view)
        return view
    }

    override fun onStart() {
        loginViewModel.registerListener(this)
        super.onStart()
    }

    override fun onDestroy() {
        loginViewModel.unregisterListener()
        super.onDestroy()
    }

    @OnClick(R.id.button_register)
    fun onClickRegister(view: View) {
        Log.i(TAG, "OnClickRegister")
        activity?.supportFragmentManager?.let { NavigationManager.goToRegisterFragment(it) }
    }

    @OnClick(R.id.button_login)
    fun onClickLogin(view: View) {
        Log.i(TAG, "OnClickLogin")
        if (edit_email.text.toString().isEmpty() || edit_password.text.toString().isEmpty()) {
            edit_email.setError("Error")
            edit_password.setError("Error")
        }
        else {
            loginViewModel.onLogin(edit_email.text.toString(), edit_password.text.toString())
        }
    }

    override fun onAuthenticateFailure() {
        edit_email.setError("Erro")
        edit_password.setError("Erro")
    }

    override fun onAuthenticateSuccess(email: String, token: String) {
        Log.i(TAG, "EMAIL - $email")
        Log.i(TAG, "TOKEN - $token")
        activity?.getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE)?.edit()
            ?.putString("email", email)
            ?.putString("token", token)
            ?.apply()
        val intent = Intent(activity, MainActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }
}
