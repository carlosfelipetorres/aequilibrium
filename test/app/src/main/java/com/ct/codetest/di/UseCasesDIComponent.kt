package com.ct.codetest.di

import com.ct.codetest.usecases.AllSparksUseCase
import com.ct.codetest.usecases.TransformersUseCase
import org.koin.dsl.module

val UseCaseDependency = module {

    factory {
        TransformersUseCase()
    }
    factory {
        AllSparksUseCase()
    }
}