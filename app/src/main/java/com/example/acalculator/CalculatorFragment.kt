package com.example.acalculator

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_calculator.*
import java.text.SimpleDateFormat
import java.util.*
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.Optional
import kotlinx.android.synthetic.main.fragment_calculator.view.*


const val EXTRA_HISTORIC = "com.example.acalculator.HISTORIC"
const val VISOR_KEY = "visor"

class CalculatorFragment : Fragment(), OnDisplayChanged {

    private val TAG = CalculatorFragment::class.java.simpleName
    private val historic:MutableList<Operation> = mutableListOf()
    private lateinit var viewModel: CalculatorViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_calculator, container, false)
        viewModel = ViewModelProviders.of(this).get(CalculatorViewModel::class.java)
        viewModel.historic.let {
            view.list_historic?.layoutManager = LinearLayoutManager(activity as Context)
            view.list_historic?.adapter = HistoricAdapter(activity as Context, R.layout.item_expression, it)
        }
        ButterKnife.bind(this, view)
        /*historic.clear()
        historic.addAll(activity?.intent?.getParcelableArrayListExtra<Operation>(EXTRA_HISTORIC)?.toMutableList()?:mutableListOf())
        view.list_historic?.layoutManager = LinearLayoutManager(activity as Context)
        view.list_historic?.adapter = HistoricAdapter(activity as Context, R.layout.item_expression, historic)

        view.last_expression?.text = if (historic.isEmpty()) "" else historic.last().result.toString()*/
        return view
    }

    override fun onStart() {
        viewModel.registerListener(this)
        super.onStart()
    }

    override fun onDisplayChanged(value: String?) {
        value?.let {
            text_visor.text = it
        }
    }

    override fun onDestroy() {
        viewModel.unregisterListener()
        super.onDestroy()
    }

    /*override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            activity?.intent?.putParcelableArrayListExtra(EXTRA_HISTORIC, ArrayList(historic))
        }
        super.onSaveInstanceState(outState)
    }*/

    @Optional
    @OnClick(R.id.button_division, R.id.button_multiplication, R.id.button_adition, R.id.button_subtraction, R.id.button_0, R.id.button_1, R.id.button_2, R.id.button_3, R.id.button_4, R.id.button_5, R.id.button_6, R.id.button_point,R.id.button_00, R.id.button_22, R.id.button_7, R.id.button_8, R.id.button_9)
    fun onClickSymbol(view: View) {
        viewModel.onClickSymbol(view.tag.toString())
    }

    @OnClick(R.id.button_delete)
    fun onClickDelete(view: View) {
        Log.i(TAG, "Click no botão <")
        if (text_visor.text.length == 1) {
            text_visor.text = "0"
        }
        else {
            text_visor.text = text_visor.text.dropLast(1)
        }
        Toast.makeText(context, "Button < at ${SimpleDateFormat("hh:mm:ss").format(Date())}", Toast.LENGTH_SHORT).show()
    }

    @OnClick(R.id.button_clear)
    fun onClickClear(view: View) {
        Log.i(TAG, "Click no botão C")
        text_visor.text = "0"
        Toast.makeText(context, "Button clear at ${SimpleDateFormat("hh:mm:ss").format(Date())}", Toast.LENGTH_SHORT).show()
    }

    @OnClick(R.id.button_equals)
    fun onClickEquals(view: View) {
        viewModel.onClickEquals()
        view.list_historic?.layoutManager = LinearLayoutManager(activity as Context)
        view.list_historic?.adapter = HistoricAdapter(activity as Context, R.layout.item_expression, viewModel.historic())
    }

    /*@Optional
    @OnClick(R.id.button_historic)
    fun onClickHistoric(view: View) {
        val intent = Intent(context, HistoricActivity::class.java)
        intent.apply {
            putParcelableArrayListExtra(EXTRA_HISTORIC, ArrayList(historic))
        }
        startActivity(intent)
        //finish()
    }*/
}
