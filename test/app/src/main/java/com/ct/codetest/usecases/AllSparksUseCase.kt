package com.ct.codetest.usecases

import com.ct.codetest.repository.Repository
import org.koin.core.KoinComponent
import org.koin.core.inject

class AllSparksUseCase : KoinComponent {

    private val mRepo : Repository by inject()

    suspend fun processAllSparksUseCase() : String {

        return mRepo.getAllSparks()
    }
}