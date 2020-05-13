package pt.ulusofona.cm.ui.fragments

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import pt.ulusofona.cm.R

const val PREFERENCES_FILE = "PREFERENCES"

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }

}
