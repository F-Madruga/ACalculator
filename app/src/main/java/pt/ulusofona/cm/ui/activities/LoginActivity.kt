package pt.ulusofona.cm.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import pt.ulusofona.cm.R
import pt.ulusofona.cm.ui.utils.NavigationManager

class LoginActivity : AppCompatActivity() {

    private val TAG = LoginActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i(TAG, "OnCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setSupportActionBar(toolbar)
        NavigationManager.goToLoginFragment(supportFragmentManager)
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
        if (supportFragmentManager.backStackEntryCount == 1) {
            finish()
        }
        else {
            super.onBackPressed()
        }
    }
}
