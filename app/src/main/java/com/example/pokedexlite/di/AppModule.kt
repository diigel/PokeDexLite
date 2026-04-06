package com.example.pokedexlite.di

import com.example.pokedexlite.data.repository.AuthRepositoryImpl
import com.example.pokedexlite.data.repository.PokemonRepositoryImpl
import com.example.pokedexlite.domain.repository.AuthRepository
import com.example.pokedexlite.domain.repository.PokemonRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    abstract fun bindPokemonRepository(impl: PokemonRepositoryImpl): PokemonRepository
}
