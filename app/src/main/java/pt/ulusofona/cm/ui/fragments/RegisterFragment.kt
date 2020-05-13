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
import kotlinx.android.synthetic.main.fragment_register.*
import pt.ulusofona.cm.R
import pt.ulusofona.cm.ui.listeners.OnRegistered
import pt.ulusofona.cm.ui.utils.NavigationManager
import pt.ulusofona.cm.ui.viewmodels.RegisterViewModel

class RegisterFragment : Fragment(), OnRegistered {

    private val TAG = LoginFragment::class.java.simpleName

    private lateinit var registerViewModel: RegisterViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.i(TAG, "OnCreateView")
        val view = inflater.inflate(R.layout.fragment_register, container, false)
        registerViewModel = ViewModelProviders.of(this).get(RegisterViewModel::class.java)
        ButterKnife.bind(this, view)
        return view
    }

    override fun onStart() {
        registerViewModel.registerListener(this)
        super.onStart()
    }

    override fun onDestroy() {
        registerViewModel.unregisterListener()
        super.onDestroy()
    }

    @OnClick(R.id.button_submit)
    fun onClickSubmit(view: View) {
        Log.i(TAG, "OnClickSubmit")
        if (edit_password.text.toString() == edit_confirm_password.text.toString()) {
            Log.i(TAG, "Register Start")
            registerViewModel.onClickSubmitRegister(
                edit_username.text.toString(),
                edit_email.text.toString(),
                edit_password.text.toString()
            )
        }
    }

    override fun onRegisteredFailure() {
        edit_username.setError("Erro")
        edit_email.setError("Erro")
        edit_password.setError("Erro")
    }

    override fun onRegisteredSuccess() {
        activity?.supportFragmentManager?.let { NavigationManager.goToLoginFragment(it) }
    }
}
