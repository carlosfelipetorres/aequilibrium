package com.ct.codetest.di

import com.ct.codetest.usecases.*
import org.koin.dsl.module

val UseCaseDependency = module {

    factory {
        TransformersUseCase()
    }
    factory {
        AllSparksUseCase()
    }
    factory {
        DeleteTransformerUseCase()
    }
    factory {
        UpdateTransformerUseCase()
    }
    factory {
        CreateTransformerUseCase()
    }
    factory {
        TransformerByIdUseCase()
    }
}