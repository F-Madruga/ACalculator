package pt.ulusofona.cm.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.Optional
import kotlinx.android.synthetic.main.fragment_calculator.*
import pt.ulusofona.cm.R
import pt.ulusofona.cm.data.local.entities.Operation
import pt.ulusofona.cm.ui.adapters.HistoryAdapter
import pt.ulusofona.cm.ui.listeners.OnDisplayChanged
import pt.ulusofona.cm.ui.listeners.OnHistoryChanged
import pt.ulusofona.cm.ui.viewmodels.CalculatorViewModel
import pt.ulusofona.cm.ui.viewmodels.HistoryViewModel

class CalculatorFragment : Fragment(), OnDisplayChanged, OnHistoryChanged {

    private val TAG = CalculatorFragment::class.java.simpleName

    private lateinit var calculatorViewModel: CalculatorViewModel
    private lateinit var historyViewModel: HistoryViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.i(TAG, "OnCreateView")
        val view = inflater.inflate(R.layout.fragment_calculator, container, false)
        calculatorViewModel = ViewModelProviders.of(this).get(CalculatorViewModel::class.java)
        historyViewModel = ViewModelProviders.of(this).get(HistoryViewModel::class.java)
        ButterKnife.bind(this, view)
        return view
    }

    override fun onStart() {
        Log.i(TAG, "OnStart")
        calculatorViewModel.registerListener(this)
        historyViewModel.registerListener(this)
        super.onStart()
    }

    override fun onDestroy() {
        Log.i(TAG, "OnDestroy")
        calculatorViewModel.unregisterListener()
        historyViewModel.unregisterListener()
        super.onDestroy()
    }

    override fun onAddOperation() {
        historyViewModel.getAll()
    }

    override fun onDisplayChanged(value: String?) {
        Log.i(TAG, "OnDisplayChanged")
        value?.let { text_visor.text = it }
    }

    override fun onHistoryChanged(history: List<Operation>) {
        Log.i(TAG, "OnHistoryChanged")
        history.let{
            list_history?.layoutManager = LinearLayoutManager(activity as Context)
            list_history?.adapter = HistoryAdapter(activity as Context, R.layout.item_expression, it)
            text_last_expression?.text = if (it.isNotEmpty()) it.last().toString() else ""
        }
    }

    @OnClick(R.id.button_equals)
    fun onClickEquals(view: View) {
        Log.i(TAG, "OnClickEquals")
        calculatorViewModel.onClickEquals()
    }

    @Optional
    @OnClick(R.id.button_division, R.id.button_multiplication, R.id.button_addiction, R.id.button_subtration, R.id.button_0, R.id.button_1, R.id.button_2, R.id.button_3, R.id.button_4, R.id.button_5, R.id.button_6, R.id.button_dot,R.id.button_00, R.id.button_second_2, R.id.button_7, R.id.button_8, R.id.button_9)
    fun onClickSymbol(view: View) {
        Log.i(TAG, "OnClickSymbol")
        calculatorViewModel.onClickSymbol(view.tag.toString())
    }

    @OnClick(R.id.button_delete)
    fun onClickDelete(view: View) {
        Log.i(TAG, "OnClickDelete")
        calculatorViewModel.onClickDelete()
    }

    @OnClick(R.id.button_clear)
    fun onClickClear(view: View) {
        Log.i(TAG, "OnClickClear")
        calculatorViewModel.onClickClear()
    }
}
