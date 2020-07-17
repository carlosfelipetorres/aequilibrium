package com.ct.codetest.viewmodels

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ct.codetest.models.transformers.AllTransformers
import com.ct.codetest.platform.LiveDataWrapper
import com.ct.codetest.usecases.TransformersUseCase
import kotlinx.coroutines.*
import org.koin.core.KoinComponent

class TransformersViewModel(
    mainDispatcher: CoroutineDispatcher,
    ioDispatcher: CoroutineDispatcher,
    private val transformersUseCase: TransformersUseCase,
    private val sharedPreferences: SharedPreferences
) : ViewModel(), KoinComponent {

    var mTransformersResponse = MutableLiveData<LiveDataWrapper<AllTransformers>>()
    val mLoadingLiveData = MutableLiveData<Boolean>()
    private val job = SupervisorJob()
    private val mUiScope = CoroutineScope(mainDispatcher + job)
    private val mIoScope = CoroutineScope(ioDispatcher + job)

    fun requestTransformersData() {
        mUiScope.launch {
            mTransformersResponse.value = LiveDataWrapper.loading()
            setLoadingVisibility(true)
            try {
                val data = mIoScope.async {
                    return@async transformersUseCase.processTransformersUseCase()
                }.await()
                mTransformersResponse.value = LiveDataWrapper.success(data)
                setLoadingVisibility(false)
            } catch (e: Exception) {
                setLoadingVisibility(false)
                mTransformersResponse.value = LiveDataWrapper.error(e)
            }
        }

    }

    private fun setLoadingVisibility(visible: Boolean) {
        mLoadingLiveData.postValue(visible)
    }

    override fun onCleared() {
        super.onCleared()
        this.job.cancel()
    }
}