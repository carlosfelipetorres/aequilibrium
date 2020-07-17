package com.ct.codetest.platform

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ct.codetest.usecases.AllSparksUseCase
import com.ct.codetest.usecases.TransformersUseCase
import com.ct.codetest.viewmodels.LoginViewModel
import com.ct.codetest.viewmodels.TransformersViewModel
import kotlinx.coroutines.CoroutineDispatcher

class BaseViewModelFactory constructor(
    private val mainDispather: CoroutineDispatcher,
    private val ioDispatcher: CoroutineDispatcher,
    private val transformersUseCase: TransformersUseCase,
    private val allSparksUseCase: AllSparksUseCase,
    private val sharedPreferences: SharedPreferences
) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(TransformersViewModel::class.java)) {
            TransformersViewModel(
                mainDispather,
                ioDispatcher,
                transformersUseCase,
                sharedPreferences
            ) as T
        } else if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            LoginViewModel(
                mainDispather,
                ioDispatcher,
                allSparksUseCase,
                sharedPreferences
            ) as T
        } else {
            throw IllegalArgumentException("ViewModel Not configured") as Throwable
        }
    }
}