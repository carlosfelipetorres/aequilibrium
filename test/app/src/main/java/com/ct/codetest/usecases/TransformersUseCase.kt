package com.ct.codetest.usecases

import com.ct.codetest.models.transformers.AllTransformers
import com.ct.codetest.repository.Repository
import org.koin.core.KoinComponent
import org.koin.core.inject

class TransformersUseCase : KoinComponent {

    private val mRepo : Repository by inject()

    suspend fun processTransformersUseCase() : AllTransformers {
        return mRepo.getTransformersData()
    }
}