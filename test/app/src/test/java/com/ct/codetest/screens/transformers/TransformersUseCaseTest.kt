package com.ct.codetest.screens.transformers

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ct.codetest.base.BaseUTTest
import com.ct.codetest.di.configureTestAppComponent
import com.ct.codetest.usecases.TransformersUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.context.startKoin
import java.net.HttpURLConnection

@RunWith(JUnit4::class)
class TransformersUseCaseTest : BaseUTTest(){

    //Target
    private lateinit var transformersUseCase: TransformersUseCase

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    val mCount = 1395

    @Before
    fun start(){
        super.setUp()
        //Start Koin with required dependencies
        startKoin{ modules(configureTestAppComponent(getMockWebServerUrl()))}
    }

    @Test
    fun test_transformers_use_case_returns_expected_value()= runBlocking{

        mockNetworkResponseWithFileContent("success_resp_list.json", HttpURLConnection.HTTP_OK)
        transformersUseCase = TransformersUseCase()

        val dataReceived = transformersUseCase.processTransformersUseCase()

        assertNotNull(dataReceived)
        assertEquals(dataReceived.transformers.size, mCount)
    }

}