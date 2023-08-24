package kg.varis.youtube.di

import kg.varis.youtube.core.network.remoteModule
import kg.varis.youtube.remote.remoteDataSource
import org.koin.core.module.Module

val koinModules = listOf<Module>(
    repoModules, viewModules,remoteModule,remoteDataSource
)