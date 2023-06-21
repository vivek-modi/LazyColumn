package com.example.lazycolumn

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val lazyColumnModule = module {
    viewModelOf(::MainActivityViewModel)
}