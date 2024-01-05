package com.skipissue.maxway.modules

import com.skipissue.maxway.data.local.DataBaseDataSource
import com.skipissue.maxway.data.local.DataBaseDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.lesson40.data.settings.Settings
import uz.gita.lesson40.data.settings.SettingsImpl

@Module
@InstallIn(SingletonComponent::class)
interface CacheModule {
    @Binds
    fun bindSettings(settingsImpl: SettingsImpl): Settings

    @Binds
    fun bindBasket(dataBaseDataSourceImpl: DataBaseDataSourceImpl):DataBaseDataSource


}