package com.ct.codetest.repository

import android.content.SharedPreferences
import com.ct.codetest.models.transformers.AllTransformers
import com.ct.codetest.network.transformers.APIService
import org.koin.core.KoinComponent
import org.koin.core.inject

class Repository : KoinComponent {

    private val mAPIService: APIService by inject()
    private val sharedPreferences by inject<SharedPreferences>()

    suspend fun getAllSparks(): String {
        return mAPIService.getAllSpark()
    }

    suspend fun getTransformersData(): AllTransformers {
        val token = sharedPreferences.getString("token", "")
        return mAPIService.getTransformers("Bearer $token")
    }


}