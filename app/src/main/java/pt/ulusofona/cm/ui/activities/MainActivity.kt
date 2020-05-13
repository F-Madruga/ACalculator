package pt.ulusofona.cm.ui.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.drawer_header.view.*
import pt.ulusofona.cm.R
import pt.ulusofona.cm.ui.fragments.PREFERENCES_FILE
import pt.ulusofona.cm.ui.utils.NavigationManager

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i(TAG, "OnCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        NavigationManager.goToCalculatorFragment(supportFragmentManager)
        setupDrawerMenu()
    }

    override fun onStart() {
        Log.i(TAG, "OnStart")
        super.onStart()
    }

    override fun onDestroy() {
        Log.i(TAG, "OnDestroy")
        super.onDestroy()
    }

    override fun onBackPressed() {
        Log.i(TAG, "OnBackPressed")
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
        Log.i(TAG, "SetupDrawerMenu")
        val toogle = ActionBarDrawerToggle(this, drawer, toolbar, R.string.drawer_open, R.string.drawer_close)
        nav_drawer.setNavigationItemSelectedListener(this)
        drawer.addDrawerListener(toogle)
        toogle.syncState()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        Log.i(TAG, "OnNavigationItemSelected")
        when(item.itemId) {
            R.id.nav_calculator -> NavigationManager.goToCalculatorFragment(supportFragmentManager)
            R.id.nav_history -> NavigationManager.goToHistoryFragment(supportFragmentManager)
            R.id.nav_settings -> NavigationManager.goToSettingsFragment(supportFragmentManager)
        }
        drawer.closeDrawer(GravityCompat.START)
        return true
    }
}
