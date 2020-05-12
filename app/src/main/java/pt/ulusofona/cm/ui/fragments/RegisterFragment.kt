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

class RegisterFragment : Fragment() {

    private val TAG = LoginFragment::class.java.simpleName

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.i(TAG, "OnCreateView")
        val view = inflater.inflate(R.layout.fragment_register, container, false)
        ButterKnife.bind(this, view)
        return view
    }

    @OnClick(R.id.button_submit)
    fun onClickSubmit(view: View) {
        Log.i(TAG, "OnClickSubmit")
    }
}
