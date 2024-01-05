package com.skipissue.maxway.data.datasource

import uz.gita.lesson40.data.settings.Settings
import javax.inject.Inject

class LanguageDataSourceImpl @Inject constructor(private val settings: Settings) :
    LanguageDataSource {

    override fun setLanguage(value: Int) {
        settings.language = value
    }

    override fun getLanguage(): Int {
        return settings.language
    }

}