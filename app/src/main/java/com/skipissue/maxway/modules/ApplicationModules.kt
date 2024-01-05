package com.skipissue.maxway.modules

import android.content.Context
import androidx.room.Room
import com.skipissue.maxway.data.api.MaxWayAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import uz.gita.lesson40.database.AppDataBase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModules {
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://fastfood.urinboev.uz/api/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDataBase {
        return Room.databaseBuilder(context, AppDataBase::class.java, "basketDataBase")
            .build()
    }


    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient()

    }


    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit): MaxWayAPI {
        return retrofit.create()
    }


}