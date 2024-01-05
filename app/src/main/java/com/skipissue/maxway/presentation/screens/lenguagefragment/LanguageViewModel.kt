package com.skipissue.maxway.presentation.screens.lenguagefragment

import androidx.lifecycle.ViewModel
import com.skipissue.maxway.data.datasource.LanguageDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LanguageViewModel @Inject constructor(private val languageDataSource: LanguageDataSource) :
    ViewModel() {

        fun setLanguage(value:Int){
            languageDataSource.setLanguage(value)
        }


}