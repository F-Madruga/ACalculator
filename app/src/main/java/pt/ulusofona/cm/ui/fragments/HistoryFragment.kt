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
import kotlinx.android.synthetic.main.fragment_history.*
import pt.ulusofona.cm.R
import pt.ulusofona.cm.data.local.entities.Operation
import pt.ulusofona.cm.ui.adapters.HistoryAdapter
import pt.ulusofona.cm.ui.listeners.OnHistoryChanged
import pt.ulusofona.cm.ui.viewmodels.CalculatorViewModel
import pt.ulusofona.cm.ui.viewmodels.HistoryViewModel

class HistoryFragment : Fragment(), OnHistoryChanged {

    private val TAG = HistoryFragment::class.java.simpleName

    private lateinit var historyViewModel: HistoryViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.i(TAG, "OnCreateView")
        val view = inflater.inflate(R.layout.fragment_history, container, false)
        ButterKnife.bind(this, view)
        historyViewModel = ViewModelProviders.of(this).get(HistoryViewModel::class.java)
        return view
    }

    override fun onStart() {
        Log.i(TAG, "OnStart")
        historyViewModel.registerListener(this)
        super.onStart()
    }

    override fun onDestroy() {
        Log.i(TAG, "OnDestroy")
        historyViewModel.unregisterListener()
        super.onDestroy()
    }

    override fun onHistoryChanged(history: List<Operation>) {
        Log.i(TAG, "OnHistoryChanged")
        history.let{
            list_history?.layoutManager = LinearLayoutManager(activity as Context)
            list_history?.adapter = HistoryAdapter(activity as Context, R.layout.item_expression, it)
        }
    }

}
