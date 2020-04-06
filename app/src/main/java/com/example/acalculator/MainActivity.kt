package com.example.acalculator


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.fragment_calculator.*


class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "O metodo onCreate foi invocado")
        setContentView(R.layout.activity_main)
        NavigationManager.goToCalculatorFragment(supportFragmentManager)

        val historic = intent?.getParcelableArrayListExtra<Operation>(EXTRA_HISTORIC)?.toMutableList()?:mutableListOf()
        list_historic?.layoutManager = LinearLayoutManager(this)
        list_historic?.adapter = HistoricAdapter(this, R.layout.item_expression, historic)
    }

    override fun onDestroy() {
        Log.i(TAG, "O metodo onDestroy foi invocado")
        super.onDestroy()
    }


    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        text_visor.text = savedInstanceState?.getString(VISOR_KEY)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            putString(VISOR_KEY, text_visor.text.toString())
        }
        super.onSaveInstanceState(outState)
    }
}
