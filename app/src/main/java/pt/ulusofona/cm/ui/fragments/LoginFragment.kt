package pt.ulusofona.cm.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import butterknife.OnClick
import pt.ulusofona.cm.R
import pt.ulusofona.cm.ui.utils.NavigationManager

class LoginFragment : Fragment() {

    private val TAG = LoginFragment::class.java.simpleName

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.i(TAG, "OnCreateView")
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        ButterKnife.bind(this, view)
        return view
    }

    @OnClick(R.id.button_register)
    fun onClickRegister(view: View) {
        Log.i(TAG, "OnClickRegister")
        activity?.supportFragmentManager?.let { NavigationManager.goToRegisterFragment(it) }
    }

    @OnClick(R.id.button_login)
    fun onClickLogin(view: View) {
        Log.i(TAG, "OnClickLogin")
        // TODO
    }
}
