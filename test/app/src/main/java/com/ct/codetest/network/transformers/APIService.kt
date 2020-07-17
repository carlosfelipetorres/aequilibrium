package com.ct.codetest.network.transformers

import com.ct.codetest.models.transformers.AllTransformers
import retrofit2.http.GET
import retrofit2.http.Header

interface APIService {

    @GET("allspark")
    suspend fun getAllSpark(): String

    @GET("transformers")
    suspend fun getTransformers(@Header("Authorization") token: String): AllTransformers

}