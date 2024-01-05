package uz.gita.lesson40.data.settings

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SettingsImpl @Inject constructor(@ApplicationContext context: Context,
) : Settings {
    private val preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE)

    override var language: Int
        get() = preferences.getInt("language",0)
        set(value) = preferences.edit().putInt("language", value).apply()


    override var suggest: String?
        get() = preferences.getString("suggest", null)
        set(value) = preferences.edit().putString("suggest", value).apply()

    override var sigInToken: String?
        get() = preferences.getString("signIn", null)
        set(value) = preferences.edit().putString("signIn", value).apply()
    override var screenPassword: String?
        get() = preferences.getString("password",null)
        set(value) =preferences.edit().putString("password",value).apply()


    override var overallSum: Int
        get() = preferences.getInt("overallSumm",0)
        set(value) =preferences.edit().putInt("overallSumm",value).apply()

    override var auth: Int
        get() = preferences.getInt("auth",-1)
        set(value) =preferences.edit().putInt("auth",value).apply()
    override var phone_number: String?
        get() = preferences.getString("phone", null)
        set(value) = preferences.edit().putString("phone", value).apply()
}