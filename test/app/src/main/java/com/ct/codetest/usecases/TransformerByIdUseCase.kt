package com.ct.codetest.usecases

import com.ct.codetest.models.transformers.Transformer
import com.ct.codetest.repository.Repository
import org.koin.core.KoinComponent
import org.koin.core.inject

class TransformerByIdUseCase : KoinComponent {

    private val mRepo : Repository by inject()

    suspend fun processTransformersUseCase(id: String) : Transformer {
        return mRepo.getTransformerByIdData(id)
    }
}