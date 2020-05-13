package pt.ulusofona.cm.ui.fragments

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

    override fun onAuthenticated(email: String, token: String) {
        Log.i(TAG, "Email = $email")
        Log.i(TAG, "Token = $token")
    }
}
