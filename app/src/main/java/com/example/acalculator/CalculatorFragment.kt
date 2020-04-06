package com.example.acalculator

import android.content.Context
import android.content.Intent
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
import androidx.recyclerview.widget.LinearLayoutManager
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.Optional
import kotlinx.android.synthetic.main.fragment_calculator.list_historic
import kotlinx.android.synthetic.main.fragment_calculator.view.*
import net.objecthunter.exp4j.ExpressionBuilder
import kotlin.collections.ArrayList

const val EXTRA_HISTORIC = "com.example.acalculator.HISTORIC"
const val VISOR_KEY = "visor"

class CalculatorFragment : Fragment() {

    private val TAG = CalculatorFragment::class.java.simpleName
    private val historic:MutableList<Operation> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_calculator, container, false)
        ButterKnife.bind(this, view)
        historic.clear()
        historic.addAll(activity?.intent?.getParcelableArrayListExtra<Operation>(EXTRA_HISTORIC)?.toMutableList()?:mutableListOf())
        view.list_historic?.layoutManager = LinearLayoutManager(activity as Context)
        view.list_historic?.adapter = HistoricAdapter(activity as Context, R.layout.item_expression, historic)

        view.last_expression?.text = if (historic.isEmpty()) "" else historic.last().result.toString()
        return view
    }


    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            activity?.intent?.putParcelableArrayListExtra(EXTRA_HISTORIC, ArrayList(historic))
        }
        super.onSaveInstanceState(outState)
    }

    @Optional
    @OnClick(R.id.button_0, R.id.button_1, R.id.button_2, R.id.button_3, R.id.button_4, R.id.button_5, R.id.button_6, R.id.button_point,R.id.button_00, R.id.button_22, R.id.button_7, R.id.button_8, R.id.button_9)
    fun onClickSymbol(view: View) {
        val symbol = view.tag.toString()
        Log.i(TAG, "Click no botão ${symbol}")
        if (text_visor.text == "0") {
            text_visor.text = symbol
        } else {
            text_visor.append(symbol)
        }
        Toast.makeText(context, "Button ${symbol} at ${SimpleDateFormat("hh:mm:ss").format(Date())}", Toast.LENGTH_SHORT).show()
    }

    @OnClick(R.id.button_division, R.id.button_multiplication, R.id.button_adition, R.id.button_subtraction)
    fun onClickOperation(view: View) {
        val operation = view.tag.toString()
        Log.i(TAG, "Click no botão ${operation}")
        text_visor.append(operation)
        Toast.makeText(context, "Button ${operation} at ${SimpleDateFormat("hh:mm:ss").format(Date())}", Toast.LENGTH_SHORT).show()
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
        Log.i(TAG, "Click no botão =")
        val expression = ExpressionBuilder(text_visor.text.toString()).build()
        val operation = Operation(text_visor.text.toString(), expression.evaluate())
        text_visor.text = operation.result.toString()
        historic.add(operation)
        last_expression?.text = operation.result.toString()
        list_historic?.layoutManager = LinearLayoutManager(activity as Context)
        list_historic?.adapter = HistoricAdapter(activity as Context, R.layout.item_expression, historic)
        Toast.makeText(context, "Button = at ${SimpleDateFormat("hh:mm:ss").format(Date())}", Toast.LENGTH_SHORT).show()
        Log.i(TAG, "O resultado da expressão é ${text_visor.text}")
    }

    @Optional
    @OnClick(R.id.button_historic)
    fun onClickHistoric(view: View) {
        val intent = Intent(context, HistoricActivity::class.java)
        intent.apply { putParcelableArrayListExtra(EXTRA_HISTORIC, ArrayList(historic)) }
        startActivity(intent)
        //finish()
    }
}
