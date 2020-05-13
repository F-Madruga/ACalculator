package pt.ulusofona.cm.ui.utils

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import pt.ulusofona.cm.R
import pt.ulusofona.cm.ui.fragments.*

abstract class NavigationManager {
    companion object {

        private val TAG = NavigationManager::class.java.simpleName

        private fun placeFragment(fm: FragmentManager, fragment: Fragment) {
            Log.i(TAG, "PlaceFragment")
            val transition = fm.beginTransaction()
            transition.replace(R.id.frame, fragment)
            transition.addToBackStack(null)
            transition.commit()
        }

        fun goToCalculatorFragment(fm: FragmentManager) {
            Log.i(TAG, "GoToCalculatorFragment")
            placeFragment(fm, CalculatorFragment())
        }

        fun goToHistoryFragment(fm: FragmentManager) {
            Log.i(TAG, "GoToHistoryFragment")
            placeFragment(fm, HistoryFragment())
        }

        fun goToLoginFragment(fm: FragmentManager) {
            Log.i(TAG, "GoToLoginFragment")
            placeFragment(fm, LoginFragment())
        }

        fun goToRegisterFragment(fm: FragmentManager) {
            Log.i(TAG, "GoToRegisterFragment")
            placeFragment(fm, RegisterFragment())
        }

        fun goToSettingsFragment(fm: FragmentManager) {
            Log.i(TAG, "GoToSettings")
            placeFragment(fm, SettingsFragment())
        }
    }
}