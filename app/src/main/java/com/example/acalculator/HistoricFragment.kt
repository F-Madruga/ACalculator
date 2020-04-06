package com.example.acalculator

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import butterknife.OnClick

class HistoricFragment : Fragment() {

    private val TAG = CalculatorFragment::class.java.simpleName

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_historic, container, false)
        ButterKnife.bind(this, view)
        val historic = arguments?.getParcelableArrayList<Operation>(EXTRA_HISTORIC)?.toMutableList()
        Log.i(TAG,historic.toString())
        Log.i(TAG, "OnCreateView")
        return view
    }



    @OnClick(R.id.button_back)
    fun onClickBack(view: View) {
        val intent = Intent(activity as Context, MainActivity::class.java)
        intent.apply { putParcelableArrayListExtra(EXTRA_HISTORIC, ArrayList()) }
        startActivity(intent)
    }
}
