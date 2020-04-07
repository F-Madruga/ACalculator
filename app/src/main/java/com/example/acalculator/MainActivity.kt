package com.example.acalculator


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_calculator.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "O metodo onCreate foi invocado")
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        NavigationManager.goToCalculatorFragment(supportFragmentManager)
        setupDrawerMenu()
        val historic = intent?.getParcelableArrayListExtra<Operation>(EXTRA_HISTORIC)?.toMutableList()?:mutableListOf()
        list_historic?.layoutManager = LinearLayoutManager(this)
        list_historic?.adapter = HistoricAdapter(this, R.layout.item_expression, historic)
    }

    override fun onDestroy() {
        Log.i(TAG, "O metodo onDestroy foi invocado")
        super.onDestroy()
    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        }
        else if (supportFragmentManager.backStackEntryCount == 1) {
            finish()
        }
        else {
            super.onBackPressed()
        }
    }

    private fun setupDrawerMenu() {
        val toogle = ActionBarDrawerToggle(this, drawer, toolbar, R.string.drawer_open, R.string.drawer_close)
        nav_drawer.setNavigationItemSelectedListener(this)
        drawer.addDrawerListener(toogle)
        toogle.syncState()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.nav_calculator -> {
                NavigationManager.goToCalculatorFragment(supportFragmentManager)
            }
            R.id.nav_historic -> NavigationManager.goToHistoricFragment(supportFragmentManager)
        }
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        text_visor.text = savedInstanceState?.getString(VISOR_KEY)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            putString(VISOR_KEY, text_visor?.text.toString())
        }
        super.onSaveInstanceState(outState)
    }
}
