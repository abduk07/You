package kg.varis.youtube.di

import kg.varis.youtube.repository.Repository
import org.koin.dsl.module

val repoModules = module {
    single { Repository(get(), get()) }
}