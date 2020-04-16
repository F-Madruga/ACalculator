package com.example.acalculator

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import butterknife.ButterKnife
import butterknife.OnClick
import kotlinx.android.synthetic.main.fragment_historic.view.*

class HistoricFragment : Fragment() {

    private val TAG = CalculatorFragment::class.java.simpleName
    private val historic = mutableListOf<Operation>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_historic, container, false)
        ButterKnife.bind(this, view)

        /*historic.clear()
        historic.addAll(activity?.intent?.getParcelableArrayListExtra<Operation>(EXTRA_HISTORIC)?.toMutableList()?: mutableListOf())
        view.list_historic.layoutManager = LinearLayoutManager(activity as Context)
        view.list_historic.adapter = HistoricAdapter(activity as Context, R.layout.item_expression, historic)*/

        return view
    }



    /*@OnClick(R.id.button_back)
    fun onClickBack(view: View) {
        val intent = Intent(activity as Context, MainActivity::class.java)
        intent.apply { putParcelableArrayListExtra(EXTRA_HISTORIC, ArrayList(historic)) }
        startActivity(intent)
    }*/
}
