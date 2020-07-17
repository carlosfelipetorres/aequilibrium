package com.ct.codetest.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.ct.codetest.BuildConfig
import com.ct.codetest.constants.TIME_OUT
import com.ct.codetest.network.transformers.APIService
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val preferencesModule = module {
    single { provideSettingsPreferences(androidApplication()) }
}

private const val PREFERENCES_FILE_KEY = "com.ct.codetest.settings_preferences"

private fun provideSettingsPreferences(app: Application): SharedPreferences =
    app.getSharedPreferences(PREFERENCES_FILE_KEY, Context.MODE_PRIVATE)