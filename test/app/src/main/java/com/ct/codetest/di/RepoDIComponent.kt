package com.ct.codetest.di

import com.ct.codetest.repository.Repository
import org.koin.dsl.module

val RepoDependency = module {

    factory {
        Repository()
    }

}