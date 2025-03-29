package com.example.base.base

import android.annotation.SuppressLint
import android.content.Context
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import com.example.base.database.model.MySample
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

class BasePreferences @Inject constructor(private val context: Context) {

    private val prefsListThemeFreeHome = "prefsListThemeFreeHome"
    private val prefsNewUser = "prefsNewUser"
    private val mPrefs = PreferenceManager.getDefaultSharedPreferences(context)


    var newUser
        get() = mPrefs.getBoolean(prefsNewUser, true)
        set(value) = mPrefs.edit { putBoolean(prefsNewUser, value) }

    var listItemsFree: ArrayList<MySample>
        get() = Gson().fromJson(
            mPrefs.getString(
                prefsListThemeFreeHome,
                Gson().toJson(arrayListOf<MySample>())
            ), object : TypeToken<ArrayList<MySample>>() {}.type
        )
        set(value) = mPrefs.edit { putString(prefsListThemeFreeHome, Gson().toJson(value)).apply() }

    companion object {

        @SuppressLint("StaticFieldLeak")
        private lateinit var INSTANCE: BasePreferences

        fun initPrefs(context: Context) {
            val appContext = context.applicationContext
            INSTANCE = BasePreferences(appContext)
        }

        fun getPrefsInstance(): BasePreferences {
            if (!::INSTANCE.isInitialized) {
                throw UninitializedPropertyAccessException("GoPreferences not initialized!")
            }
            return INSTANCE
        }
    }

    private fun getPrefName(name: String): String {
        return "pref$name"
    }
}