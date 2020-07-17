package com.ct.codetest.usecases

import com.ct.codetest.repository.Repository
import org.koin.core.KoinComponent
import org.koin.core.inject

class DeleteTransformerUseCase : KoinComponent {

    private val mRepo : Repository by inject()

    suspend fun processTransformersUseCase(id: String) : Any {
        return mRepo.deleteTransformers(id)
    }
}