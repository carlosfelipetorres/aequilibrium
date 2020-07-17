package com.ct.codetest.usecases

import com.ct.codetest.models.transformers.Transformer
import com.ct.codetest.repository.Repository
import org.koin.core.KoinComponent
import org.koin.core.inject

class CreateTransformerUseCase : KoinComponent {

    private val mRepo : Repository by inject()

    suspend fun processTransformersUseCase(transformer: Transformer) : Transformer {
        return mRepo.createTransformer(transformer)
    }
}